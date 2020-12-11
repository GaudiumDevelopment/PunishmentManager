package me.superbiebel.punishmentmanager;

import de.leonhard.storage.Config;
import de.leonhard.storage.LightningBuilder;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.DataType;
import lombok.Getter;
import me.lucko.helper.Events;
import me.lucko.helper.Schedulers;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.superbiebel.punishmentmanager.commands.PunishCommand;
import me.superbiebel.punishmentmanager.commands.SystemCommand;
import me.superbiebel.punishmentmanager.data.managers.DataHandlerManager;
import me.superbiebel.punishmentmanager.data.managers.CacheManager;
import me.superbiebel.punishmentmanager.data.managers.DatabaseManager;
import me.superbiebel.punishmentmanager.listeners.JoinListener;
import me.superbiebel.punishmentmanager.listeners.LeaveListener;
import me.superbiebel.punishmentmanager.offenseprocessing.OffenseExecutorFactoryManager;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PunishmentManager extends ExtendedJavaPlugin {
    @Getter
    private static final String configVersion = "indev";
    @Getter
    private static String version = null;
    @Getter
    private static boolean debugMode;
    //private static FileConfiguration config;
    @Getter
    private static File configFile;

    private static Config config;
    @Getter
    private static PunishmentManager plugin;
    @Getter
    private static final String separator = System.getProperty("file.separator");

    private static final Date date = new Date();
    private static final SimpleDateFormat formatter = new SimpleDateFormat(" dd_MM_yyyy hh_mm_ss_SSS");
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
    -initialize the cache|async
    -initialize the database|async
    -----able to interact with database------
    -start downloading data to cache|async
    -when all of this is done and has completed successfully allow everything to proceed
     */
    @Override
    public void enable() {
        plugin = this;
        version = super.getDescription().getVersion();
        loadConfig();
        try {
            Log.initLog();
        } catch (IOException e) {
            Log.fatalError("COULD NOT INIT LOGFILE",false,true,false);
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        Log.info("LOGGING STARTS AT " + formatter.format(date),false,false,true);
        checkDebugMode();
        if (!checkConfigVersion()){
            Log.fatalError("Config version does not correspond with the version that is required by this plugin",false,true,true);
            Log.fatalError("Please back up and then delete your config so we can generate a new one on startup!",false,true,true);
            Log.fatalError("Config version is: " + config.getString("config_version"),false, true,true);
            Log.fatalError("Config version should be: " + configVersion,false, true,true);
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        if (!config.getBoolean("database.enabled")) {
            Log.fatalError("Database has not been enabled! please fill in the credentials and set the option to 'true' (without quotes)",false,true,true);
        }
        try {
            loadEvents();
            loadCommands();
            Schedulers.async().call(()->{
                DataHandlerManager.instantiate();
                return null;
            });
            Schedulers.async().call(()->{
                CacheManager.initCache(config.getString("cache.type"));
                return null;
            });
            Schedulers.async().call(()->{
                DatabaseManager.instantiate(config.getString("database.choice"));
                return null;
            });
            Schedulers.async().call(()->{
                OffenseExecutorFactoryManager.instantiate();
                return null;
            });
        } catch (Exception e) {
            Log.logException(e, Log.LogLevel.FATALERROR, false, false, true, true, true);
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
    }

    @Override
    public void disable() {
        try {
        CacheManager.getCache().close();
        DatabaseManager.getDatabase().shutdown();
        DataHandlerManager.getDataHandler().shutdown();
        } catch (NullPointerException throwable) {
            Log.warning("The MySQL datasource was null, which means it wasn't started (should not happen). Check above console for errors!",false,true,true);
        } catch (Exception e) {
            Log.logException(e, Log.LogLevel.FATALERROR,false,false,true,true,true);
        }
        try {

        } catch (NullPointerException throwable) {
            Log.warning("The Cache datasource was null, which means it wasn't started (should not happen). Check above console for errors!",false,true,true);
        }
        Log.closeLog();
        Bukkit.getServer().getLogger().info("PunishmentManager has been disabled");
    }



    public void loadConfig() {
        Log.info("loading config...",false,true,false);
        configFile = new File(plugin.getDataFolder().getAbsolutePath() + separator + "configNEW.yml");
        config = LightningBuilder.fromFile(configFile).setConfigSettings(ConfigSettings.PRESERVE_COMMENTS).setDataType(DataType.SORTED).createConfig();
        config.addDefaultsFromInputStream(super.getResource("config.yml"));
        
    }

    public void checkDebugMode() {
        debugMode = config.getBoolean("debug");
        if (debugMode){
            Log.debug("Debug mode has been enabled! There will be extensive logging!",false,true,true);
        } else {
            Log.info("Debug mode has been disabled, debug messages will not be sent!",false,true,true);
        }

    }

    public boolean checkConfigVersion() {
        Log.debug("Checking config version...",false,true,true);
        boolean status;
        if (!config.getString("config_version").equalsIgnoreCase(configVersion)) {
            Bukkit.getPluginManager().disablePlugin(this);
            status = false;
        } else {
            status = true;
        }
        return status;
    }


    /*public void initMySQL() {
        Log.debug("Checking Mysql config version...",false,true,true,"");
            MySQL.instantiate(
                config.getString("MySQL.host"),
                config.getString("MySQL.username"),
                config.getString("MySQL.password"),
                config.getString("MySQL.port"),
                config.getString("MySQL.db"),
                config.getString("MySQL.useSSL"));
            MySQL.initializeTables();
        }*/



    public static void loadEvents() {
        Log.debug("Loading events...",false, true,true);
        
        Events.subscribe(AsyncPlayerPreLoginEvent.class, EventPriority.MONITOR).handler((e)->{
            if (!(e.getLoginResult() == AsyncPlayerPreLoginEvent.Result.ALLOWED)){
                JoinListener joinListener = new JoinListener();
                joinListener.handlePreJoin(e);
            }
        }).bindWith(getPlugin());
        Events.subscribe(PlayerJoinEvent.class, EventPriority.MONITOR).handler((e)-> {
            JoinListener joinListener = new JoinListener();
            joinListener.handleJoin(e);
        });
        
        Events.subscribe(PlayerQuitEvent.class,EventPriority.MONITOR).handler(new LeaveListener()::handleQuit).bindWith(getPlugin());
        Events.subscribe(PlayerKickEvent.class,EventPriority.MONITOR).handler(new LeaveListener()::handleKick).bindWith(getPlugin());
        Log.debug("Events Loaded!",false,true,true);
    }



    public void loadCommands() {
        Log.debug("Loading commands",false,true,true);
        Log.debug("loading the /punish command...",false,true,true);
        this.getCommand("punish").setExecutor(new PunishCommand());
        Log.debug("/punish loaded",false,true,true);
        Log.debug("loading /pmanager",false,true,true);
        SystemCommand systemCommand = new SystemCommand();
        this.getCommand("pmanager").setExecutor(systemCommand);
        this.getCommand("pmanager").setTabCompleter(systemCommand);
        Log.debug("/pmanager is loaded",false,true,true);
    }
    public static Config giveConfig() {
        return config;
    }




}
