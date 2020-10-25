package me.superbiebel.punishmentmanager;

import me.lucko.helper.Events;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.superbiebel.punishmentmanager.commands.PunishCommand;
import me.superbiebel.punishmentmanager.commands.SystemCommand;
import me.superbiebel.punishmentmanager.data.Cache;
import me.superbiebel.punishmentmanager.data.MySQL;
import me.superbiebel.punishmentmanager.listeners.JoinListener;
import me.superbiebel.punishmentmanager.listeners.LeaveListener;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PunishmentManager extends ExtendedJavaPlugin {

    private static String configVersion = "indev";
    private static String version = null;
    private static boolean debugMode;
    private static FileConfiguration config;
    private static PunishmentManager plugin;

    public static String getConfigVersion() {
        return configVersion;
    }

    @Override
    public void disable() {
        try {
        MySQL.getMysqlDataSource().close();
        } catch (NullPointerException throwable) {
            Log.warning("The MySQL datasource was null, which means it wasn't started (should not happen). Check above console for errors!",false,true,true);
        }
        try {
            Cache.getCacheDataSource().close();
        } catch (NullPointerException throwable) {
            Log.warning("The Cache datasource was null, which means it wasn't started (should not happen). Check above console for errors!",false,true,true);
        }
        Bukkit.getServer().getLogger().info("The plugin has been disabled");;
    }



    public void loadConfig() {
        Log.info("loading config...",false,true,true);
        this.saveDefaultConfig();
        config= this.getConfig();
    }

    public void setDebugMode() {
        debugMode = config.getBoolean("debug");
        if (debugMode){

        }
        Log.debug("Debug mode has been enabled! There will be extensive logging!",false,true,true);
    }

    public boolean checkConfigVersion() {
        Log.debug("Checking config version...",false,true,true);
        boolean status;
        if (!config.getString("config_version").equalsIgnoreCase(configVersion)) {
            Log.fatalError("The config version doesn't correspond with the version that is needed for this plugin version!",false,true,true);
            Log.fatalError("Please back up and then delete your config so we can generate a new one on startup!",false,true,true);
            Bukkit.getPluginManager().disablePlugin(this);
            status = false;
        } else {
            status = true;
        }
        Log.debug("Config version is: " + config.getString("config_version"),false, true,true);
        return status;
    }


    public void initMySQL() {
        Log.debug("Checking Mysql config version...",false,true,true);
            MySQL.configureConnection(
                config.getString("MySQL.host"),
                config.getString("MySQL.username"),
                config.getString("MySQL.password"),
                config.getString("MySQL.port"),
                config.getString("MySQL.db"),
                config.getString("MySQL.useSSL"));
            MySQL.initializeTables(config.getString("MySQL.db"));
        }



    public static void loadEvents() {
        Log.debug("Loading events...",false, true,true);
        Events.subscribe(AsyncPlayerPreLoginEvent.class).handler(JoinListener::new).bindWith(getPlugin());

        Events.subscribe(PlayerQuitEvent.class).handler(new LeaveListener()::handleQuit).bindWith(getPlugin());
        Events.subscribe(PlayerKickEvent.class).handler(new LeaveListener()::handleKick).bindWith(getPlugin());
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


    public static boolean getDebugMode() {
        return debugMode;
    }
    public static FileConfiguration giveConfig() {
        return config;
    }
    public static PunishmentManager getPlugin() {
        return plugin;
    }

    public static String getVersion() {
        return version;
    }

    @Override
    public void enable() {
        plugin = this;
        version = super.getDescription().getVersion();
        loadConfig();

        configVersion = config.getString("config_version");
        if (checkConfigVersion() && config.getBoolean("MySQL.enabled")) {
            setDebugMode();
            loadEvents();
            loadCommands();
            Cache.initCache(config.getString("MySQL.db"));
            initMySQL();
            Log.debug("Everything has been enabled",false,true,true);
        } else if (!config.getBoolean("MySQL.enabled")) {
            for (int i = 0; i < 5; i++) {
                Log.fatalError("MYSQL HAS BEEN DISABLED!!! FILL IN THE CREDENTIALS AND ENABLE MYSQL!!!",false,true,true);
            }
            Log.warning("I know that it spams the above message but this is the storage, without storage nothing will work",false,true,true);


            Bukkit.getPluginManager().disablePlugin(plugin);
        } else {
            Log.warning("Something went wrong, I don't know what went wrong but something went wrong",false,true,true);
        }
            if (!this.isEnabled()) {
                Log.fatalError("STARTUP COULD NOT BE COMPLETED, PLEASE CHECK FOR ERRORS IN THE CONSOLE!!!",false,true,true);
            }
    }



}
