package me.superbiebel.punishmentmanager.offenseprocessing.abstraction;

public interface OffenseProcessorFactory {
    void init() throws Exception;
    OffenseProcessor giveOffenseProcessor() throws Exception;

}
