package me.superbiebel.punishmentmanager.data;

import com.zaxxer.hikari.HikariConfig;
import me.superbiebel.punishmentmanager.PunishmentManager;

public class Cache {
    private static boolean isInitialized;
    private static HikariConfig cacheConfig;

    public static void initCache(String db) {
        if (!isInitialized) {
            cacheConfig.setJdbcUrl("jdbc:h2:" + PunishmentManager.getPlugin().getDataFolder() + " " + db);
        }
    }
}
