package me.superbiebel.punishmentmanager.offenseprocessing.abstraction;

public interface OffenseProcessorFactory {
    public void init() throws Exception;
    public OffenseProcessor giveOffenseProcessor() throws Exception;

}
