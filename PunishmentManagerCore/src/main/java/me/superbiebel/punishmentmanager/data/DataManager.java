package me.superbiebel.punishmentmanager.data;

public class DataManager {
    public void init(boolean sync) throws Exception{
        /*ThreadContext threadContext = sync ? ThreadContext.SYNC : ThreadContext.ASYNC;
        Log.debug("threadcontext for data initialization:" + threadContext.name());
        Scheduler scheduler = Schedulers.get(threadContext);
        
        
        Provider dataHandlerProvider = new DataHandlerProvider();
        Provider cacheProvider = new CacheProvider();
        Provider databaseProvider = new DatabaseProvider();
        OffenseProcessorFactory offenseProcessorFactory = OffenseProcessorFactoryManager.getOffenseProcessorFactory();
        
        
        scheduler.callLater(()->{
            Log.debug("Cacheprovider init");
            TimingUtil timer = new TimingUtil().start();
            cacheProvider.init();
            timer.stop();
            Log.debug("Cacheprovider init complete (took " + timer.stop().calcDifferenceInNanos() + " nanoseconds/" + timer.calcDifferenceInMillis()+" ms)");
            return null;
        },60);
        scheduler.callLater(()->{
            Log.debug("Databaseprovider init");
            TimingUtil timer = new TimingUtil().start();
            databaseProvider.init();
            timer.stop();
            Log.debug("Databaseprovider init complete (took " + timer.stop().calcDifferenceInNanos() + " nanoseconds/" + timer.calcDifferenceInMillis()+" ms)");
            return null;
        },60);
        scheduler.callLater(()->{
            Log.debug("dataHandlerProvider init");
            TimingUtil timer = new TimingUtil().start();
            dataHandlerProvider.init();
            timer.stop();
            Log.debug("dataHandlerProvider init complete (took " + timer.stop().calcDifferenceInNanos() + " nanoseconds/" + timer.calcDifferenceInMillis()+" ms)");
            return null;
        },60);*/
    }

    public void shutdown(boolean sync) throws Exception{

    }

    public void kill() throws Exception {

    }
}
