package me.superbiebel.punishmentmanager.utils;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Log {

    private static final String namePrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c");
    private static final String debugPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &f[&8DEBUG&f] &f&l>> ");
    private static final String infoPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &f[INFO&f] &l>> ");
    private static final String warningPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &e[&6WARNING&e] &l>> ");
    private static final String fatalErrorPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &c[&4ERROR&c] &4&l>> ");


    public static void debug(String msg, boolean sendInGame,boolean sendToConsole, boolean logToFile) {

        if (PunishmentManager.getDebugMode()) {
            if (sendInGame) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("punishmentmanager.log.debug")) {
                        player.sendMessage(debugPrefix + msg);
                    }
                }
            }
            if (sendToConsole) {
                Bukkit.getServer().getLogger().info(debugPrefix + msg);
            }
        }
    }

    public static void info(String msg, boolean sendInGame,boolean sendToConsole, boolean logToFile) {
        if (sendInGame) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("punishmentmanager.log.info")) {
                player.sendMessage(infoPrefix + msg);
            }
        }}
        if (sendToConsole) {
            Bukkit.getServer().getLogger().info(infoPrefix + msg);
        }
    }

    public static void warning(String msg, boolean sendInGame,boolean sendToConsole, boolean logToFile) {
        if (sendInGame) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("punishmentmanager.log.warning")) {
                    player.sendMessage(warningPrefix + msg);
                }
            }
        }
        if (sendToConsole) {
            Bukkit.getServer().getLogger().warning(warningPrefix + msg);
        }
    }
    public static void fatalError(String msg, boolean sendInGame,boolean sendToConsole, boolean logToFile) {
        if (sendToConsole) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("punishmentmanager.log.fatalerror")) {
                    player.sendMessage(fatalErrorPrefix + msg);
                }
            }
        }
        if (sendToConsole) {
            Bukkit.getServer().getLogger().severe(fatalErrorPrefix + msg);
        }
    }
    public static String getNamePrefix() {
        return namePrefix;
    }
}
