package me.superbiebel.punishmentmanager.data.managers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.data.layers.Cache;

/**
 * Initializes the cache.
 */

public class CacheManager {
    @Getter
    private static boolean isInitialized = false;
    @Getter
    private static Cache cache;

    public static void initCache(String cacheType) throws Exception{
        //uses reflection to instantiate the database layer
        if (!isInitialized) {
            Class clazz = Class.forName(cacheType);
            Cache cache = (Cache) clazz.getDeclaredConstructor().newInstance();
            cache.init();
            isInitialized = true;
        } else {
            throw new IllegalStateException("Cache cannot be initialized multiple times!");
        }
    }
}
