package me.superbiebel.punishmentmanager.data.abstraction.managers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import me.superbiebel.punishmentmanager.data.abstraction.Service;
import me.superbiebel.punishmentmanager.utils.Log;

public class ServiceManager {
    private final List<Service> serviceRegistry = new CopyOnWriteArrayList<>();

    public void initServices(boolean forceInit) throws Exception {
        serviceRegistry.parallelStream().forEach(service->{
            try {
                service.startup(forceInit);
            } catch (Exception e) {
                Log.logException(e, Log.LogLevel.FATALERROR,false,false,true,true,true);
            }
        });
    }
    public void initServices() throws Exception {
        initServices(false);
    }

    public void registerService(Service service){
        serviceRegistry.add(service);
    }
}
