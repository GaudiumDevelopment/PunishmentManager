package me.superbiebel.punishmentmanager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.superbiebel.punishmentmanager.Utils.Log;
import me.superbiebel.punishmentmanager.commands.PunishCommand;
import me.superbiebel.punishmentmanager.menusystem.PlayerMenuUtility;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.HashMap;

public final class PunishmentManager extends ExtendedJavaPlugin {

    private static boolean debugMode;
    private static FileConfiguration config;
    private static PunishmentManager plugin;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private static String prefix;
    private static HikariDataSource ds;
    private static HikariConfig Config;

    @Override
    public void enable() {
        loadConfig();
        loadEvents();
        loadCommands();
        Log.debug("everything has been enabled");
    }

    @Override
    public void disable() {
        Log.debug("The plugin has been disabled");
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

}
