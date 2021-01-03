package me.superbiebel.punishmentmanager.data.providers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.abstraction.Cache;
import me.superbiebel.punishmentmanager.utils.Log;
import me.superbiebel.punishmentmanager.utils.ReflectionUtils;


public class CacheProvider implements Provider{
    @Getter
    private static Cache cache;
    
    @Override
    public void init() throws Exception{
        //uses reflection to instantiate the database layer
        String className = PunishmentManager.giveConfig().getString("reflectionpath.cachedriver");
        long firstTime = System.currentTimeMillis();
        cache = (Cache) ReflectionUtils.stringToInstance(className);
        long measurement = System.currentTimeMillis() - firstTime;
        Log.debug("took " + measurement + "ms to make a " + className + " instance");
        cache.init();
    }
}
