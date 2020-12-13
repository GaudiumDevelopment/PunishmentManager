package me.superbiebel.defaultoffenseprocessor;

import me.superbiebel.punishmentmanager.offenseprocessing.OffenseProcessor;
import me.superbiebel.punishmentmanager.offenseprocessing.OffenseProcessorFactory;

public class DefaultOffenseProcessorFactory implements OffenseProcessorFactory {
    @Override
    public OffenseProcessor giveOffenseExecutor() {
        return new DefaultOffenseProcessor();
    }
}
