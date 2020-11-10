package me.superbiebel.punishmentmanager.data.databases.managers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.databases.Database;
import me.superbiebel.punishmentmanager.data.mysql.MySQLDatabase;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.Bukkit;

public class DatabaseManager {
    private static boolean isInstantiated = false;
    @Getter
    private static Database database;
    private static int timesCalled = 0;

    public static Database Instantiate(choice dbchoice) throws Exception {

        if (!isInstantiated && timesCalled <= 1) {
            if (dbchoice == choice.MYSQL) {
                Log.debug("Initializing MySQL...",false,true,true,"");
                database = new MySQLDatabase();
                database.initialize();
            } else {
                try {
                    throw new IllegalArgumentException("Tried to instantiate unknown database, should NOT happen. Please check above logs for errors, also check you database choice as it was not recognized!");
                } catch (IllegalArgumentException e) {
                    Log.LogException(e, Log.LogLevel.FATALERROR, false, false, true, false, true);
                    Bukkit.getPluginManager().disablePlugin(PunishmentManager.getPlugin());
                }
            }






        /*switch (dbchoice) {
            case MYSQL:
                Log.debug("Initializing MySQL...",false,true,true,"");
                database = new MySQLDatabase();
            case UNKNOWN:
                try {
                    throw new IllegalArgumentException("Tried to instantiate unknown database, should NOT happen. Please check above logs for errors, also check you database choice as it was not recognized!");
                } catch (IllegalArgumentException e) {
                    Log.LogException(e, Log.LogLevel.FATALERROR, false, false, true, false, true);
                    Bukkit.getPluginManager().disablePlugin(PunishmentManager.getPlugin());
                }
        }
        } else {
            try {
                throw new InstantiationException("Database is already instantiated!");
            } catch (InstantiationException e) {
                Log.LogException(e, Log.LogLevel.FATALERROR,false,false,true,false,true);
            }
            return null;
        }*/
        }
        return database;
    }
        public static choice convertToEnum (String convertable) {
            if (convertable.equalsIgnoreCase(choice.MYSQL.toString())) {
                Log.debug("Mysql was chosen", false, true, true, "");
                return choice.MYSQL;
            } else {
            /*try {
                throw new IllegalArgumentException("The db choice was not recognized! Disabling plugin now.....");
            } catch (IllegalArgumentException e) {
                Log.LogException(e, Log.LogLevel.FATALERROR,false,false,true,false,true);
                Bukkit.getPluginManager().disablePlugin(PunishmentManager.getPlugin());
                return choice.UNKNOWN;
            }*/
                return choice.UNKNOWN;
            }
        }
    enum choice {
        MYSQL(){
            @Override
            public String toString() {
                return "MySQL";
            }
        },
        UNKNOWN(){
            @Override
            public String toString() {
                return "unknown";
            }
        }


    }}
