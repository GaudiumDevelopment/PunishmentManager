package me.superbiebel.punishmentmanager.data.abstraction.service.managers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import me.superbiebel.punishmentmanager.data.abstraction.service.Service;
import me.superbiebel.punishmentmanager.data.abstraction.service.ServiceType;
import me.superbiebel.punishmentmanager.data.abstraction.service.services.LoginInfoLoggerService;
import me.superbiebel.punishmentmanager.utils.Log;
import org.jetbrains.annotations.NotNull;

public class ServiceManager {
    private static final ConcurrentMap<ServiceType,Service> serviceRegistry = new ConcurrentHashMap<>();
    private static final int MAX_SERVICE_SIZE = 1;
    private final AtomicInteger serviceCount = new AtomicInteger(0);
    private final AtomicBoolean servicesRegisterComplete = new AtomicBoolean(false);
    private final AtomicBoolean serviceStartupComplete = new AtomicBoolean(false);
    @Getter
    private final CountDownLatch serviceRegisterCountDown = new CountDownLatch(MAX_SERVICE_SIZE);


    @Getter
    private LoginInfoLoggerService loginInfoLoggerService;

    public void initServices(boolean forceInit) {
        if (!(servicesRegisterComplete.get()) || forceInit) {
        servicesRegisterComplete.getAndSet(true);
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
        serviceStartupComplete.set(true);
        } else {
            throw new IllegalStateException("Could not init services because not all services are registered");
        }
    }
    public void initServices() {
        initServices(false);
    }

    public void registerService(@NotNull Service service) {
        if (MAX_SERVICE_SIZE <= serviceCount.get()) {
            throw new IndexOutOfBoundsException("Too many services have been trying to be registered!");
        }

        serviceRegistry.put(service.getType(), service);
        addToServiceCount();
    }

    public void shutdown(){
        serviceRegistry.values().parallelStream().forEach(service -> {
            try {
                service.shutdown();
            } catch (Exception e) {
                Log.logException(e, Log.LogLevel.FATALERROR);
            }
        });
    }
    private void addToServiceCount() {
        serviceCount.incrementAndGet();
        serviceRegisterCountDown.countDown();
    }
}
