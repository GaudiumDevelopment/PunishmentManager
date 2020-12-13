package me.superbiebel.punishmentmanager.data.managers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.layers.Cache;

/**
 * Initializes the cache.
 */

public class CacheManager {
    @Getter
    private static boolean isInitialized = false;
    @Getter
    private static Cache cache;

    public static void initCache() throws Exception{
        //uses reflection to instantiate the database layer
        String className = PunishmentManager.giveConfig().getString("cache.type");
        if (!isInitialized) {
            Class clazz = Class.forName(className);
            Cache cache = (Cache) clazz.getDeclaredConstructor().newInstance();
            cache.init();
            isInitialized = true;
        } else {
            throw new IllegalStateException("Cache cannot be initialized multiple times!");
        }
    }
}
