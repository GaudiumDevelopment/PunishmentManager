package me.superbiebel.offenseprocessingdataabstraction.providers;

import lombok.Getter;
import me.superbiebel.offenseprocessingdataabstraction.abstraction.OffenseProcessingDatabase;

public class OffenseProcessingDatabaseProvider implements OffenseProcessingProvider{
    @Getter
    OffenseProcessingDatabase database;
    
    @Override
    public void init(String pathToClass) throws Exception {
        System.out.println(pathToClass);
        Class clazz = Class.forName(pathToClass);
        database = (OffenseProcessingDatabase) clazz.getDeclaredConstructor().newInstance();
        database.offenseProcessingDatabaseInit();
    }
}
