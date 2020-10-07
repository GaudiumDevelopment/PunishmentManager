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

    private static final String configVersion = "indev";
    private static final String Version = "indev";
    private static boolean debugMode;
    private static FileConfiguration config;
    private static PunishmentManager plugin;


    @Override
    public void enable() {
        plugin = this;
        loadConfig();
        if (checkConfigVersion() && config.getBoolean("MySQL.enabled")) {
            setDebugMode();
            loadEvents();
            loadCommands();
            Cache.initCache(config.getString("MySQL.db"));
            initMySQL();
            Log.debug("Everything has been enabled");
        } else if (!config.getBoolean("MySQL.enabled")) {
            for (int i = 0; i < 5; i++) {
                Log.fatalError("MYSQL HAS BEEN DISABLED!!! FILL IN THE CREDENTIALS AND ENABLE MYSQL!!!");
            }
            Log.warning("I know that it spams the above message but this is the storage, without storage nothing will work");


            Bukkit.getPluginManager().disablePlugin(plugin);
        } else {
            Log.warning("Something went wrong");
        }
            if (!this.isEnabled()) {
                Log.fatalError("STARTUP COULD NOT BE COMPLETED, PLEASE CHECK FOR ERRORS IN THE CONSOLE!!!");
            }
    }

    @Override
    public void disable() {
        if (!(MySQL.getMysqlDataSource() == null)) {
            MySQL.getMysqlDataSource().close();
        }
        Log.debug("The plugin has been disabled");
    }



    public void loadConfig() {
        Log.info("loading config...");
        this.saveDefaultConfig();
        config= this.getConfig();
    }

    public void setDebugMode() {
        debugMode = config.getBoolean("debug");
        if (debugMode){

        }
        Log.debug("Debug mode has been enabled! There will be extensive logging!");
    }


    public boolean checkConfigVersion() {
        Log.debug("Checking config version...");
        boolean status;
        if (!config.getString("config_version").equals(configVersion)) {
            Log.fatalError("The config version doesn't correspond with the version that is needed for this plugin version!");
            Log.fatalError("Please back up and then delete your config so we can generate a new one on startup!");
            Bukkit.getPluginManager().disablePlugin(this);
            status = false;
        } else {
            status = true;
        }
        Log.debug("Config version is: " + config.getString("config_version"));
        return status;
    }


    public void initMySQL() {
        Log.debug("Checking Mysql config version...");
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
        Log.debug("Loading events...");
        Events.subscribe(AsyncPlayerPreLoginEvent.class).handler(JoinListener::new).bindWith(getPlugin());

        Events.subscribe(PlayerQuitEvent.class).handler(new LeaveListener()::handleQuit).bindWith(getPlugin());
        Events.subscribe(PlayerKickEvent.class).handler(new LeaveListener()::handleKick).bindWith(getPlugin());
        Log.debug("Events Loaded!");
    }



    public void loadCommands() {
        Log.debug("Loading commands");
        Log.debug("loading the /punish command...");
        this.getCommand("punish").setExecutor(new PunishCommand());
        Log.debug("/punish loaded");
        Log.debug("loading /pmanager");
        SystemCommand systemCommand = new SystemCommand();
        this.getCommand("pmanager").setExecutor(systemCommand);
        this.getCommand("pmanager").setTabCompleter(systemCommand);
        Log.debug("/pmanager is loaded");   
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


}
