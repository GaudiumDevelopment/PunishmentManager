package me.superbiebel.offenseprocessingdataabstraction.providers;

import lombok.Getter;
import me.superbiebel.offenseprocessingdataabstraction.abstraction.OffenseProcessingDataHandler;

public class OffenseProcessingDataHandlerProvider implements OffenseProcessingProvider{
    @Getter
    private OffenseProcessingDataHandler dataHandler;
    
    @Override
    public void init(String pathToClass) throws Exception {
        Class clazz = Class.forName(pathToClass);
        dataHandler = (OffenseProcessingDataHandler) clazz.getDeclaredConstructor().newInstance();
        dataHandler.offenseProcessingDataHandlerInit();
    }
}
