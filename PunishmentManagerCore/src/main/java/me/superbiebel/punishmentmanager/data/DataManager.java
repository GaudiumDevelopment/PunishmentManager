package me.superbiebel.punishmentmanager.data;

import me.lucko.helper.Schedulers;
import me.lucko.helper.promise.ThreadContext;
import me.lucko.helper.scheduler.Scheduler;
import me.superbiebel.punishmentmanager.data.providers.CacheProvider;
import me.superbiebel.punishmentmanager.data.providers.DataHandlerProvider;
import me.superbiebel.punishmentmanager.data.providers.DatabaseProvider;
import me.superbiebel.punishmentmanager.data.providers.Provider;
import me.superbiebel.punishmentmanager.offenseprocessing.abstraction.OffenseProcessorFactory;
import me.superbiebel.punishmentmanager.offenseprocessing.abstraction.OffenseProcessorFactoryManager;
import me.superbiebel.punishmentmanager.utils.Log;

public class DataManager {
    public void init(boolean sync) throws Exception{
        ThreadContext threadContext = sync ? ThreadContext.SYNC : ThreadContext.ASYNC;
        Log.debug("threadcontext for data initialization:" + threadContext.name());
        Scheduler scheduler = Schedulers.get(threadContext);
        
        
        Provider dataHandlerProvider = new DataHandlerProvider();
        Provider cacheProvider = new CacheProvider();
        Provider databaseProvider = new DatabaseProvider();
        OffenseProcessorFactory offenseProcessorFactory = OffenseProcessorFactoryManager.getOffenseProcessorFactory();
        
        
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
    }
}
