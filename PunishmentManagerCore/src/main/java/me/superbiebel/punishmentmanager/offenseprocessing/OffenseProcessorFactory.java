package me.superbiebel.punishmentmanager.offenseprocessing;

public interface OffenseProcessorFactory {
    public OffenseProcessor giveOffenseExecutor() throws Exception;
    public void init() throws Exception;
}
