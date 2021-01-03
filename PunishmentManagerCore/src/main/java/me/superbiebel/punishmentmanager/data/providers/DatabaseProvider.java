package me.superbiebel.punishmentmanager.data.providers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.abstraction.Database;
import me.superbiebel.punishmentmanager.utils.Log;
import me.superbiebel.punishmentmanager.utils.ReflectionUtils;

public class DatabaseProvider implements Provider{
    @Getter
    private static Database database;
    @Override
    public void init() throws Exception {
        //uses reflection to instantiate the database layer
        String className = PunishmentManager.giveConfig().getString("reflectionpath.databasedriver");
        database = (Database) ReflectionUtils.stringToInstance(className, Log.LogLevel.DEBUG);
        database.init();
        }
}
