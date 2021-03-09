package me.superbiebel.punishmentmanager.data.abstraction.service.managers;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import me.superbiebel.punishmentmanager.data.abstraction.service.Service;
import me.superbiebel.punishmentmanager.data.abstraction.service.ServiceType;
import me.superbiebel.punishmentmanager.data.abstraction.service.services.LoginInfoLoggerService;
import me.superbiebel.punishmentmanager.utils.Log;

public class ServiceManager {
    private final List<Service> serviceRegistryList = new CopyOnWriteArrayList<>();
    private final ConcurrentMap<ServiceType,Service> serviceRegistry = new ConcurrentHashMap<>();
    private final int MAX_SERVICE_SIZE = 1; //TODO: this should be increased whenever a new service is added.
    private final AtomicInteger serviceCount = new AtomicInteger(0);

    @Getter
    LoginInfoLoggerService loginInfoLoggerService;

    public void initServices(boolean forceInit) throws Exception {
        serviceRegistry
                .values()
                .parallelStream()
                .forEach(service->{
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
        if (MAX_SERVICE_SIZE <= serviceCount.get()) {
            throw new IndexOutOfBoundsException("Too many services have been trying to be registered!");
        }
        serviceRegistry.put(service.getType(), service);
        serviceCount.incrementAndGet();
    }
}
