package me.superbiebel.punishmentmanager.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.lucko.helper.Schedulers;
import me.superbiebel.punishmentmanager.utils.Log;

public class Cache {
    private static boolean isInitialized;
    private static HikariConfig cacheDataSourceConfig;
    private static HikariDataSource cacheDataSource;

    public static void initCache(String db) {
        if (!isInitialized) {
            Schedulers.async().run(()-> {
                isInitialized = true;
                cacheDataSourceConfig = new HikariConfig();
                //stole some code from the AdvancedBan plugin from DevLeoko. This is actually his code but adapted to me.
                String driverClassName = "org.hsqldb.jdbc.JDBCDriver";
                try {
                    Class.forName(driverClassName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                cacheDataSourceConfig.setDriverClassName(driverClassName);
                cacheDataSourceConfig.setJdbcUrl("jdbc:hsqldb:mem:hsqldb.lock_file=false");
                cacheDataSourceConfig.setUsername("PunishmentManager");
                cacheDataSourceConfig.setPassword("");
                Log.info("Don't worry about the error about a driver not supported, this is alright. Do NOT report this to my github!");
                cacheDataSource = new HikariDataSource(cacheDataSourceConfig);

            })
        ;} else {
            Log.warning("Tried to initialize the cache, but it was already initialized, pls report to developer.");
        }
    }
    public static HikariDataSource getCacheDataSource() {
        return cacheDataSource;
    }
}
