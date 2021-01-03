package me.superbiebel.punishmentmanager.data;

import me.lucko.helper.Schedulers;
import me.lucko.helper.promise.ThreadContext;
import me.lucko.helper.scheduler.Scheduler;
import me.superbiebel.offenseprocessingdataabstraction.providers.OffenseProcessingCacheProvider;
import me.superbiebel.offenseprocessingdataabstraction.providers.OffenseProcessingDataHandlerProvider;
import me.superbiebel.offenseprocessingdataabstraction.providers.OffenseProcessingDatabaseProvider;
import me.superbiebel.offenseprocessingdataabstraction.providers.OffenseProcessingProvider;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.providers.CacheProvider;
import me.superbiebel.punishmentmanager.data.providers.DataHandlerProvider;
import me.superbiebel.punishmentmanager.data.providers.DatabaseProvider;
import me.superbiebel.punishmentmanager.data.providers.Provider;
import me.superbiebel.punishmentmanager.utils.Log;

public class DataManager {
    public void init(boolean sync) throws Exception{
        ThreadContext threadContext = sync ? ThreadContext.SYNC : ThreadContext.ASYNC;
        Log.debug("threadcontext for data initialization:" + threadContext.name());
        Scheduler scheduler = Schedulers.get(threadContext);
        
        
        Provider dataHandlerProvider = new DataHandlerProvider();
        Provider cacheProvider = new CacheProvider();
        Provider databaseProvider = new DatabaseProvider();
        
        
        OffenseProcessingProvider offenseProcessingCacheProvider = new OffenseProcessingCacheProvider();
        OffenseProcessingProvider offenseProcessingDatabaseProvider = new OffenseProcessingDatabaseProvider();
        OffenseProcessingProvider offenseProcessingDatahandlerProvider = new OffenseProcessingDataHandlerProvider();
        
        
        scheduler.callLater(()->{
            cacheProvider.init();
            return null;
        },60);
        scheduler.callLater(()->{
            databaseProvider.init();
            return null;
        },60);
        scheduler.callLater(()->{
            dataHandlerProvider.init();
            return null;
        },60);
        scheduler.callLater(()->{
            offenseProcessingCacheProvider.init(PunishmentManager.giveConfig().getString("reflectionpath.standalonecachedriver")); //TODO: fill in class path
            return null;
        },60);
        scheduler.callLater(()->{
            offenseProcessingDatabaseProvider.init(PunishmentManager.giveConfig().getString("reflectionpath.standalonedatabasedriver")); //TODO: fill in class path
            return null;
        },60);
        scheduler.callLater(()->{
            offenseProcessingDatahandlerProvider.init(PunishmentManager.giveConfig().getString("reflectionpath.standalonedatahandlerdriver")); //TODO: fill in class path
            return null;
        },60);
    }
}
