package me.superbiebel.punishmentmanager.utils;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Log {


    private static final String namePrefix = ColorUtils.translateColorCodes("&r&4&lPunishment&b&lManager&6&c");
    private static final String debugPrefix = ColorUtils.translateColorCodes("&r&4&lPunishment&b&lManager&6&c &f[&8DEBUG&f] &f&l>>");
    private static final String infoPrefix = ColorUtils.translateColorCodes("&r&4&lPunishment&b&lManager&6&c &f[INFO&f] &l>>");
    private static final String warningPrefix = ColorUtils.translateColorCodes("&r&4&lPunishment&b&lManager&6&c &e[&6WARNING&e] &l>>");
    private static final String fatalErrorPrefix = ColorUtils.translateColorCodes("&r&4&lPunishment&b&lManager&6&c &c[&4ERROR&c] &4&l>>");


    public static void debug(String msg) {

        if (PunishmentManager.getDebugMode()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("punishmentmanager.log.debug")) {
                    player.sendMessage(debugPrefix + msg);
                }
            }
            Bukkit.getServer().getLogger().info(debugPrefix + msg);
        }
    }

    public static void info(String msg) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("punishmentmanager.log.info")) {
                player.sendMessage(infoPrefix + msg);
            }
            Bukkit.getServer().getLogger().info(infoPrefix + msg);
        }
    }

    public static void warning(String msg) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("punishmentmanager.log.warning")) {
                player.sendMessage(warningPrefix + msg);
            }
        }
        Bukkit.getServer().getLogger().warning(warningPrefix + msg);
    }
    public static void fatalError(String msg) {
            for (Player player: Bukkit.getOnlinePlayers()){
                if (player.hasPermission("punishmentmanager.log.fatalerror")) {
                    player.sendMessage(fatalErrorPrefix + msg);
                }
        }
        Bukkit.getServer().getLogger().severe(fatalErrorPrefix + msg);
    }
}
