package me.superbiebel.punishmentmanager.utils;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static final String namePrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c");
    private static final String debugPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &f[&8DEBUG&f] &f&l>> ");
    private static final String infoPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &f[INFO&f] &l>> ");
    private static final String warningPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &e[&6WARNING&e] &l>> ");
    private static final String fatalErrorPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &c[&4ERROR&c] &4&l>> ");


    private static Date date = new Date();
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
    private static String separator = System.getProperty("file.separator");
    private static String logsFolderSaveLocation = PunishmentManager.getPlugin().getDataFolder().getAbsolutePath() + separator + "logs";
    private static File logsFolder;
    private static File logFile;
    private static final String logFileSaveLocation = logsFolderSaveLocation + separator + "log_" + formatter.format(date) + ".log";
    private static PrintWriter printer;

    public static boolean initLog() throws IOException {


        logsFolder = new File(logsFolderSaveLocation);
        logFile = new File(logFileSaveLocation);

        if (!logsFolder.exists()) {
            logsFolder.mkdirs();
        }
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        printer = new PrintWriter(logFile);
        Log.fatalError(logFileSaveLocation,
                false,true,false,"");
        return true;
    }

    public static void debug(@NotNull String msg, boolean sendInGame,boolean sendToConsole, boolean logToFile, @Nullable String executorName) {
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
            if (logToFile) {
                printer.println("[DEBUG]: " + msg);
                printer.flush();
            }
        }
    }

    public static void info(@NotNull String msg, boolean sendInGame,boolean sendToConsole, boolean logToFile, @Nullable String executorName) {
        if (sendInGame) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("punishmentmanager.log.info")) {
                    player.sendMessage(infoPrefix + msg);
                }
            }
        }
        if (sendToConsole) {
            Bukkit.getServer().getLogger().info(infoPrefix + msg);
        }
        if (logToFile) {
            printer.println("[INFO]: " + msg);
            printer.flush();
        }
    }

    public static void warning(@NotNull String msg, boolean sendInGame,boolean sendToConsole, boolean logToFile, @Nullable String executorName) {
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
        if (logToFile) {
            printer.println("[WARNING]: " + msg);
            printer.flush();
        }
    }

    public static void fatalError(@NotNull String msg, boolean sendInGame, boolean sendToConsole, boolean logToFile, @Nullable String executorName) {
        if (sendInGame) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("punishmentmanager.log.fatalerror")) {
                    player.sendMessage(fatalErrorPrefix + msg);
                }
            }
        }
        if (sendToConsole) {
            Bukkit.getServer().getLogger().severe(fatalErrorPrefix + msg);
        }
        if (logToFile) {
            printer.println("[FATALERROR]: " + msg);
            printer.flush();
        }
    }
    public static boolean log(String msg, LogLevel loglevel, boolean sendInGame, boolean sendToConsole, boolean logToFile, @Nullable String executorName) {
        switch (loglevel) {
            case DEBUG:
                Log.debug(msg, sendInGame, sendToConsole, logToFile, executorName);
                return true;
            case INFO:
                Log.info(msg, sendInGame, sendToConsole, logToFile, executorName);
                return true;
            case WARNING:
                Log.warning(msg, sendInGame, sendToConsole, logToFile, executorName);
                return true;
            case FATALERROR:
                Log.fatalError(msg, sendInGame, sendToConsole, logToFile, executorName);
                return true;
            case NOT_FOUND:
                return false;
        }
        return false;
    }
    public static LogLevel convertToLogLevel(String logLevelString) {
        if (logLevelString.equalsIgnoreCase("debug")) {
            return LogLevel.DEBUG;
        } else if (logLevelString.equalsIgnoreCase("info")) {
            return LogLevel.INFO;
        } else if (logLevelString.equalsIgnoreCase("warning")) {
            return LogLevel.WARNING;
        } else if (logLevelString.equalsIgnoreCase("fatalerror")) {
            return LogLevel.FATALERROR;
        } else {
            return LogLevel.NOT_FOUND;
        }
    }
    public static String getNamePrefix() {
        return namePrefix;
    }
    public enum LogLevel {
        DEBUG,INFO,WARNING,FATALERROR,NOT_FOUND
    }
}
