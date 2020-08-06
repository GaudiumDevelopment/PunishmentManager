package me.superbiebel.punishmentmanager.Utils;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.Bukkit;

public class Log {
    public static void debug(String msg) {

        if (PunishmentManager.getDebugMode()) {
            Bukkit.getServer().getLogger().info(msg);
        }
    }
    public static void info(String msg) {
        Bukkit.getServer().getLogger().info(msg);
    }
    public static void error(String msg) {
        Bukkit.getServer().getLogger().warning(msg);
    }
    public static void fatalError(String msg) {
        Bukkit.getServer().getLogger().severe(msg);
    }
}
