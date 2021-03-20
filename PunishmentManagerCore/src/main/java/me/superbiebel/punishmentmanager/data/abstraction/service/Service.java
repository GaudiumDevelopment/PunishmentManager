package me.superbiebel.punishmentmanager.data.abstraction.service;

public interface Service {

    void startup(boolean forceStart) throws Exception;
    void shutdown() throws Exception;
    void kill() throws Exception;
    ServiceType getType();
    boolean isInit();
}
