package me.superbiebel.punishmentmanager;

import cloud.commandframework.CommandTree;
import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import de.leonhard.storage.Config;
import de.leonhard.storage.LightningBuilder;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.DataType;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import lombok.Getter;
import me.lucko.helper.Schedulers;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.superbiebel.punishmentmanager.api.ServicesAPI;
import me.superbiebel.punishmentmanager.commands.PunishCommand;
import me.superbiebel.punishmentmanager.commands.SystemCommand;
import me.superbiebel.punishmentmanager.data.abstraction.DataController;
import me.superbiebel.punishmentmanager.data.abstraction.service.managers.ServiceManager;
import me.superbiebel.punishmentmanager.listeners.DataServiceStartupSafety;
import me.superbiebel.punishmentmanager.listeners.LeaveInfoLogger;
import me.superbiebel.punishmentmanager.listeners.LoginInfoLogger;
import me.superbiebel.punishmentmanager.punishsystem.PunishSystem;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;


public class PunishmentManager extends ExtendedJavaPlugin {
    @Getter
    private static PunishmentManager plugin;
    @Getter
    private static ServicesAPI servicesAPI;

    private static PunishSystem punishSystem;

    final Function<CommandSender, CommandSender> mapperFunction = Function.identity();
    
    @SuppressWarnings("FieldCanBeLocal")
    private PaperCommandManager<CommandSender> commandManager;
    final Function<CommandTree<CommandSender>, CommandExecutionCoordinator<CommandSender>> executionCoordinatorFunction = CommandExecutionCoordinator.simpleCoordinator();
    final Function<ParserParameters, CommandMeta> commandMetaFunction = p -> CommandMeta.simple()
                                                                                        .with(CommandMeta.DESCRIPTION, p.get(StandardParameters.DESCRIPTION, "No description"))
                                                                                        .build();
    
    private AnnotationParser<CommandSender> annotationParser;
    
    @Getter
    private static final String CONFIG_VERSION = "indev";
    @Getter
    private static String version = null;
    @Getter
    private static boolean debugMode;
    @Getter
    private static File configFile;

    private static Config config;

    @Getter
    private static ServiceManager serviceManager;

    @Getter
    private static DataController dataController;
    
    @Getter
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private static final Date date = new Date();
    private static final SimpleDateFormat formatter = new SimpleDateFormat(" dd_MM_yyyy hh_mm_ss_SSS");

    DataServiceStartupSafety dataServicesChecker;
    /*
    STARTUP PROCESS:
    -set the plugin instance
    -get the plugin version
    -initialize logging
    -load the config file
    -----able to log something------
    -get and check the version from the config file
    -load the events
    -load the commands
    -initialize all data services
    -----able to interact with database------
    -when all of this is done and has completed successfully allow everything to proceed
     */
    @Override
    public void enable() {
        plugin = this;
        version = super.getDescription().getVersion();
        //load the config file
        loadConfig();
        try {
            Log.initLog();
        } catch (IOException e) {
            Log.fatalError("COULD NOT INIT LOGFILE", false, true, false);
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        Log.info("LOGGING STARTS AT " + formatter.format(date), false, false, true);
        checkDebugMode();
        // -----able to log something------
        initDataServiceSafety();
        if (!checkConfigVersion()) {
            Log.fatalError("Config version does not correspond with the version that is required by this plugin", false, true, true);
            Log.fatalError("Please back up and then delete your config so we can generate a new one on startup!", false, true, true);
            Log.fatalError("Config version is: " + config.getString("config_version"), false, true, true);
            Log.fatalError("Config version should be: " + CONFIG_VERSION, false, true, true);
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        if (!config.getBoolean("database.enabled")) {
            Log.fatalError("Database has not been enabled! please fill in the credentials and set the option to 'true' (without quotes)", false, true, true);
        }
        try {
            loadCommands();
            Schedulers.async().call(()->{
                Log.debug("Starting up ServiceManager and waiting for all services to be registered.");
                serviceManager = new ServiceManager();
                serviceManager.getServiceStartupCountDown().await();
                Log.debug("Starting up all services...");
                serviceManager.initServices();
                Log.debug("All services started up.");
                dataController = new DataController();
                Schedulers.sync().run(()->{
                    loadDataNeedingEvents();
                    shutdownDataServiceSafety();
                });
                return null;
            });
            Schedulers.async().call(() -> {
                punishSystem = new PunishSystem();
                punishSystem.init();
                return null;
            });

        } catch (Exception e) {
            Log.logException(e, Log.LogLevel.FATALERROR, false, false, true, true, true);
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        servicesAPI = new ServicesAPI();
    }

    @Override
    public void disable() {
        serviceManager.shutdown();
        Log.closeLog();
        Bukkit.getServer().getLogger().info("PunishmentManagerCore has been disabled");
        plugin = null;
    }



    private void loadConfig() {
        Log.info("loading config...",false,true,false);
        configFile = new File(plugin.getDataFolder().getAbsolutePath() + FILE_SEPARATOR + "config.yml");
        config = LightningBuilder.fromFile(configFile).setConfigSettings(ConfigSettings.PRESERVE_COMMENTS).setDataType(DataType.SORTED).createConfig();
        config.addDefaultsFromInputStream(super.getResource("config.yml"));
        
    }

    private void checkDebugMode() {
        debugMode = config.getBoolean("debug");
        if (debugMode){
            Log.debug("Debug mode has been enabled! There will be extensive logging!",false,true,true);
        } else {
            Log.info("Debug mode has been disabled, debug messages will not be sent!",false,true,true);
        }

    }

    private boolean checkConfigVersion() {
        Log.debug("Checking config version...",false,true,true);
        boolean status;
        if (!config.getString("config_version").equalsIgnoreCase(CONFIG_VERSION)) {
            Bukkit.getPluginManager().disablePlugin(this);
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    private void initDataServiceSafety() {
        //load data service safety
        Log.debug("Loading data service safety");
        dataServicesChecker = new DataServiceStartupSafety();
        dataServicesChecker.init();
        Log.debug("Loaded data service safety!");
    }

    private void shutdownDataServiceSafety() {
        Log.debug("Shutting down data service safety...");
        dataServicesChecker.shutdown();
        Log.debug("Data service safety shut down");
    }

    private void loadDataNeedingEvents() {
        Log.debug("Loading data needing events...",false, true,true);
        LoginInfoLogger loginInfoLogger = new LoginInfoLogger();
        loginInfoLogger.init();
        LeaveInfoLogger leaveInfoLogger = new LeaveInfoLogger();
        leaveInfoLogger.init();
        Log.debug("Events data needing loaded!",false,true,true);
    }




    private void loadCommands() throws Exception {
        Log.debug("Instantiating commandmanager",false,true,true);
        commandManager = new PaperCommandManager<>(plugin,executionCoordinatorFunction,mapperFunction,mapperFunction);
        Log.debug("Commandmanager instantiated",false,true,true);
        commandManager.registerBrigadier();
        annotationParser = new AnnotationParser<>( commandManager, CommandSender.class, commandMetaFunction);
        annotationParser.parse(new PunishCommand());
        annotationParser.parse(new SystemCommand());
        
    }
    public static Config giveConfig() {
        return config;
    }
}
