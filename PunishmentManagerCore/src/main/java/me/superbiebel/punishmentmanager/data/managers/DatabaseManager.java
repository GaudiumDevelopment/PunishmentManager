package me.superbiebel.punishmentmanager.data.managers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.data.layers.Database;

public class DatabaseManager {
    @Getter
    private static boolean isInitialized = false;
    @Getter
    private static Database database;
    private static int timesCalled = 0;

    public static Database instantiate(String className) throws Exception {
        //uses reflection to instantiate the database layer
        Class clazz = Class.forName(className);
        Database db = (Database) clazz.getDeclaredConstructor().newInstance();
        db.init();
        database = db;
        return database;
        }
}
