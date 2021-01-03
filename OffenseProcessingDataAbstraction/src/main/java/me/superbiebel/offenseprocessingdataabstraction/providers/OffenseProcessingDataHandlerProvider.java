package me.superbiebel.offenseprocessingdataabstraction.providers;

import lombok.Getter;
import me.superbiebel.offenseprocessingdataabstraction.abstraction.OffenseProcessingDataHandler;

public class OffenseProcessingDataHandlerProvider implements OffenseProcessingProvider{
    @Getter
    private OffenseProcessingDataHandler dataHandler;
    
    @Override
    public void init(String pathToClass) throws Exception {
        System.out.println(pathToClass);
        System.out.println("initiating standalone");
        Class clazz = Class.forName(pathToClass);
        dataHandler = (OffenseProcessingDataHandler) clazz.getDeclaredConstructor().newInstance();
        dataHandler.offenseProcessingDataHandlerInit();
    }
}
