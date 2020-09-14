package me.superbiebel.punishmentmanager;

import me.lucko.helper.Events;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.superbiebel.punishmentmanager.commands.PunishCommand;
import me.superbiebel.punishmentmanager.commands.SystemCommand;
import me.superbiebel.punishmentmanager.listeners.JoinListener;
import me.superbiebel.punishmentmanager.listeners.LeaveListener;
import me.superbiebel.punishmentmanager.mysql.MySQL;
import me.superbiebel.punishmentmanager.utils.Log;
import me.superbiebel.punishmentmanager.utils.PlayerDataUtility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public final class PunishmentManager extends ExtendedJavaPlugin {

    private static boolean debugMode;
    private static FileConfiguration config;
    private static PunishmentManager plugin;
    private static final HashMap<Player, PlayerDataUtility> playerDataUtilityMap = new HashMap<>();


    @Override
    public void enable() {
        plugin = this;
        loadConfig();
        if (!this.config.getString("config_version").equalsIgnoreCase("indev")) {
            Log.fatalError("The config version doesn't correspond with the version that is needed for this plugin version!");
            Log.fatalError("Please delete your config so we can generate a new one on startup!");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        if (!config.getBoolean("MySQL.enabled")){
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
        loadEvents();
        loadCommands();
        if (config.getBoolean("MySQL.enabled")){
        MySQL.configureConnection(
                config.getString("MySQL.host"),
                config.getString("MySQL.username"),
                config.getString("MySQL.password"),
                config.getString("MySQL.port"),
                config.getString("MySQL.db"),
                config.getString("MySQL.useSSL"));

        MySQL.initializeTables(config.getString("MySQL.db"));
        } else {
            for (int i = 0; i <= 5; i++ ){
            Log.fatalError("MYSQL HAS NOT BEEN ENABLED! NOTHING CAN BE SAVED OR ACCESSED! PLEASE FILL IN THE DETAILS OF THE MYSQL DATABASE BEFORE DOING ANYTHING ELSE!");}
        }

        Log.debug("Everything has been enabled");
    }}

    @Override
    public void disable() {
        Log.debug("The plugin has been disabled");
    }



    public void loadConfig() {
        Log.info("loading config");
        this.saveDefaultConfig();
        this.config= this.getConfig();
        this.debugMode = this.config.getBoolean("debug");
        Log.debug("Debug mode has been enabled! There <ill be extensive logging!");}


    public static void loadEvents() {
        Log.debug("Loading events...");
        Events.subscribe(AsyncPlayerPreLoginEvent.class).handler(joinEvent -> {
            new JoinListener(joinEvent);

        } ).bindWith(getPlugin());

        Events.subscribe(PlayerQuitEvent.class).handler(quitEvent -> {
            new LeaveListener().handleQuit(quitEvent);
        } ).bindWith(getPlugin());
        Events.subscribe(PlayerKickEvent.class).handler(kickEvent -> {
            new LeaveListener().handleKick(kickEvent);
        } ).bindWith(getPlugin());
        Log.debug("Events Loaded!");
    }

    public void loadCommands() {
        Log.debug("Loading commands");
        Log.debug("loading the /punish command...");
        this.getCommand("punish").setExecutor(new PunishCommand());
        Log.debug("/punish loaded");
        Log.debug("loading /pmanager");
        this.getCommand("pmanager").setExecutor(new SystemCommand());
        Log.debug("/pmanager is loaded");
    }


    //Originally from the video of Kody Simpson and repurposed from playerMenuUtility to PlayerDataUtility
    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerDataUtility getPlayerDataUtility(Player p) {
        PlayerDataUtility playerDataUtility;
        if (!(playerDataUtilityMap.containsKey(p))) { //See if the player has a playerdatautility "saved" for them
            //This player doesn't. Make one for them add add it to the hashmap
            playerDataUtility = new PlayerDataUtility(p);
            playerDataUtilityMap.put(p, playerDataUtility);

            return playerDataUtility;
        } else {
            return playerDataUtilityMap.get(p); //Return the object by using the provided player
        }
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
