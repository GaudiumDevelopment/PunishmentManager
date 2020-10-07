package me.superbiebel.punishmentmanager.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.lucko.helper.Schedulers;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.Log;

public class Cache {
    private static boolean isInitialized;
    private static HikariConfig cacheDataSourceConfig;
    private static HikariDataSource cacheDataSource;

    public static void initCache(String db) {
        if (!isInitialized) {
            Schedulers.async().run(()-> {
                cacheDataSourceConfig = new HikariConfig();
                //stole some code from the AdvancedBan plugin from DevLeoko
                String driverClassName = "org.hsqldb.jdbc.JDBCDriver";
                try {
                    Class.forName(driverClassName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                cacheDataSourceConfig.setDriverClassName(driverClassName);
                cacheDataSourceConfig.setJdbcUrl("jdbc:hsqldb:mem:file:" + PunishmentManager.getPlugin().getDataFolder().getPath() + "/data/storage;hsqldb.lock_file=false");
                cacheDataSourceConfig.setUsername("PunishmentManager");
                cacheDataSourceConfig.setPassword("");
                Log.info("Don't worry about the error about a driver not supported, this is alright. Do NOT report this to my github!");
                cacheDataSource = new HikariDataSource(cacheDataSourceConfig);
            })
        ;}
    }
}
