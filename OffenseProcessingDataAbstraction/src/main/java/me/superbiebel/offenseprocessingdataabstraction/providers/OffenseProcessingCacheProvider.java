package me.superbiebel.offenseprocessingdataabstraction.providers;

import lombok.Getter;
import me.superbiebel.offenseprocessingdataabstraction.abstraction.OffenseProcessingCache;

public class OffenseProcessingCacheProvider implements OffenseProcessingProvider {
    @Getter
    OffenseProcessingCache cache;
    
    @Override
    public void init(String pathToClass) throws Exception {
        Class clazz = Class.forName(pathToClass);
        cache = (OffenseProcessingCache) clazz.getDeclaredConstructor().newInstance();
        cache.offenseProcessingCacheInit();
    }
}
