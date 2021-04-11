package me.superbiebel.punishmentmanager.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Log {

    @Getter private static final String namePrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c");
    @Getter private static final String debugPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &f[&8DEBUG&f] &f&l>> ");
    @Getter private static final String infoPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &f[INFO&f] &l>> ");
    @Getter private static final String warningPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &e[&6WARNING&e] &l>> ");
    @Getter private static final String fatalErrorPrefix = ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &c[&4ERROR&c] &4&l>> ");

    private static final Date date = new Date();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
    private static final String separator = System.getProperty("file.separator");
    private static final String logsFolderSaveLocation = PunishmentManager.getPlugin().getDataFolder().getAbsolutePath() + separator + "logs";
    private static File logsFolder;
    private static File logFile;
    private static final String logFileSaveLocation = logsFolderSaveLocation + separator + "log_" + formatter.format(date) + ".log";
    private static PrintWriter printer;

    public static void initLog() throws IOException {

        logsFolder = new File(logsFolderSaveLocation);
        logFile = new File(logFileSaveLocation);

        if (!logsFolder.exists()) {
            if (!logsFolder.mkdirs()) throw new IOException("Could not create full directory structure! Check if the prgram can read and write in the plugins data folder");
        }
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        printer = new PrintWriter(logFile);
        Log.debug(logFileSaveLocation,
                false, true, false);
    }
    public static void closeLog() {
        printer.close();
    }

    public static synchronized void debug(@NotNull final String msg, final boolean sendInGame, final boolean sendToConsole, final boolean logToFile) {
        if (PunishmentManager.isDebugMode()) {
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

    public static synchronized void info(@NotNull final String msg, final boolean sendInGame, final boolean sendToConsole, final boolean logToFile) {
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

    public static synchronized void warning(@NotNull final String msg, final boolean sendInGame, final boolean sendToConsole, final boolean logToFile) {
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

    public static synchronized void fatalError(@NotNull final String msg, final boolean sendInGame, final boolean sendToConsole, final boolean logToFile) {
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
    public static synchronized boolean log(@NotNull final String msg, final LogLevel loglevel, final boolean sendInGame, final boolean sendToConsole, final boolean logToFile) {
        switch (loglevel) {
            case DEBUG:
                Log.debug(msg, sendInGame, sendToConsole, logToFile);
                return true;
            case INFO:
                Log.info(msg, sendInGame, sendToConsole, logToFile);
                return true;
            case WARNING:
                Log.warning(msg, sendInGame, sendToConsole, logToFile);
                return true;
            case FATALERROR:
                Log.fatalError(msg, sendInGame, sendToConsole, logToFile);
                return true;
            case NOT_FOUND:
                return false;
        }
        return false;
    }

    public static synchronized void logException(final Throwable e, final LogLevel logLevel, final boolean sendInGame, final boolean sendFullInGame, final boolean sendToConsole, final boolean sendFullInConsole, final boolean logToFile){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stacktrace = sw.toString();
        String cause = e.toString();
        if (sendFullInGame){
            Log.log(stacktrace,logLevel,true,false,logToFile);
        } else {
            Log.log(cause,logLevel,sendInGame,false,logToFile);
        }
        if (sendFullInConsole) {
            Log.log(stacktrace,logLevel,false,true,logToFile);
        } else {
            Log.log(cause,logLevel,false,sendToConsole,logToFile);
        }
        Log.log(stacktrace,logLevel,false,false,logToFile);
    }
    public static synchronized void logException(final Throwable throwable, final LogLevel logLevel) {
        logException(throwable, logLevel,false,false,true,true,true);
    }


    public static synchronized LogLevel convertToLogLevel(final String logLevelString) {
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
    
    public static synchronized void debug(@NotNull String msg) {
        Log.debug(msg,false,true,true);
    }

    public enum LogLevel {
        DEBUG,INFO,WARNING,FATALERROR,NOT_FOUND
    }
}
