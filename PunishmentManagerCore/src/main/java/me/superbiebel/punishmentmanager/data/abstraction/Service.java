package me.superbiebel.punishmentmanager.data.abstraction;

public interface Service {

    void startup(boolean forceStart) throws Exception;
    void shutdown() throws Exception;
    void kill() throws Exception;
    String getName();
    boolean isInit();
}
