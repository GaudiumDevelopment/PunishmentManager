package me.superbiebel.punishmentmanager;

import me.superbiebel.punishmentmanager.Utils.Log;
import me.superbiebel.punishmentmanager.menusystem.PlayerMenuUtility;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class PunishmentManager extends JavaPlugin {

    private static boolean debugMode;
    private static FileConfiguration config;
    private static PunishmentManager plugin;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        Log.info("PunishmentManager is starting up!");
        loadConfig();
        loadEvents();
        loadCommands();
    }

    @Override
    public void onDisable() {

        Log.debug("The plugin has been disabled");
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

    public void loadConfig() {
        Log.info("loading config");
        this.saveDefaultConfig();
        this.config= this.getConfig();
        this.debugMode = this.config.getBoolean("debug");
        Log.debug("Debug mode has been enabled! Extensive logging will be enabled!");}

    public static void loadEvents() {
        Log.debug("Loading events");
    }
    public static void loadCommands() {
        Log.debug("Loading events");
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
