package me.superbiebel.punishmentmanager;

import me.superbiebel.punishmentmanager.Utils.Log;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class PunishmentManager extends JavaPlugin {

    private static boolean debugMode;
    private static FileConfiguration config;
    private static PunishmentManager plugin;

    @Override
    public void onEnable() {
        loadConfig();
    }

    @Override
    public void onDisable() {
        Log.debug("On disable has been called!");
    }

    public void loadConfig() {
        this.saveDefaultConfig();
        this.config= this.getConfig();
        this.config.getBoolean("debug");
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
