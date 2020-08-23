package me.superbiebel.punishmentmanager;

import com.zaxxer.hikari.HikariDataSource;
import me.lucko.helper.Schedulers;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.lucko.helper.promise.Promise;
import me.superbiebel.punishmentmanager.mysql.MySQL;
import me.superbiebel.punishmentmanager.utils.Log;
import me.superbiebel.punishmentmanager.commands.PunishCommand;
import me.superbiebel.punishmentmanager.commands.SystemCommand;
import me.superbiebel.punishmentmanager.menusystem.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public final class PunishmentManager extends ExtendedJavaPlugin {

    private static boolean debugMode;
    private static FileConfiguration config;
    private static PunishmentManager plugin;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private static HikariDataSource ds;



    private static ResultSet OffenseListGuiData;

    @Override
    public void enable() {
        plugin = this;
        loadConfig();
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

        MySQL.initializeTables();} else {
            for (int i = 0; i <= 5; i++ ){
            Log.fatalError("MYSQL HAS NOT BEEN ENABLED! NOTHING CAN BE SAVED OR ACCESSED! PLEASE FILL IN THE DETAILS OF THE MYSQL DATABASE BEFORE DOING ANYTHING ELSE!");}
        }

        Log.debug("Everything has been enabled");
    }

    @Override
    public void disable() {

        Log.debug("The plugin has been disabled");
    }

    public static void getAllData(String caller) {
        if (!caller.equalsIgnoreCase("system")) {
            if (!caller.equalsIgnoreCase("console")) {
                Player callerPlayer = Bukkit.getPlayer(caller);
                Promise<Void> fetchAllDataPromise = Promise.start().thenRunSync(()-> callerPlayer.sendMessage("Syncing...")).thenRunAsync(()->{
                    try {
                        Connection con = MySQL.getDataSource().getConnection();
                        Log.debug("Fetching OffenseListGui...");
                        PreparedStatement OffenseListGuiDataStmt = con.prepareStatement("");
                        OffenseListGuiData = OffenseListGuiDataStmt.executeQuery();
                        Log.debug("OffenseListGui data fetched...");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }).thenRunSync(()->{
                    callerPlayer.sendMessage("Sync complete!");
                });
            }

        }

    }


    public void loadConfig() {
        Log.info("loading config");
        this.saveDefaultConfig();
        this.config= this.getConfig();
        this.debugMode = this.config.getBoolean("debug");
        Log.debug("Debug mode has been enabled! Extensive logging will be enabled!");}


    public static void loadEvents() {
        Log.debug("Loading events");
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



    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them add add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p); //Return the object by using the provided player
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
    public static ResultSet getOffenseListGuiData() {
        return OffenseListGuiData;
    }


}
