package me.superbiebel.punishmentmanager.data.managers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.data.databases.Database;

public class DatabaseManager {
    @Getter
    private static boolean isInitialized = false;
    @Getter
    private static Database database;
    private static int timesCalled = 0;

    public static Database Instantiate(String className) throws Exception {
        Class clazz = Class.forName(className);
        Database db = (Database) clazz.getDeclaredConstructor().newInstance();
        db.init();
        database = db;
        return database;
        }
}
