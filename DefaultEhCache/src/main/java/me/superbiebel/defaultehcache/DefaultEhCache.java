package me.superbiebel.defaultehcache;

import me.superbiebel.offenseprocessingdataabstraction.abstraction.OffenseProcessingCache;
import me.superbiebel.punishmentmanager.data.abstraction.Cache;

public class DefaultEhCache implements Cache, OffenseProcessingCache {
    
    @Override
    public void init() throws Exception {
    
    }
    
    @Override
    public void transfer() throws Exception {
    
    }
    
    @Override
    public void close() throws Exception {
    
    }
    
    @Override
    public <T> void putCachedInventory(T gui) throws Exception {
    
    }
    
    @Override
    public <T> T getCachedInventory(String key) throws Exception {
        return null;
    }
    
    @Override
    public void offenseProcessingCacheInit() throws Exception {
    
    }
    
    @Override
    public void offenseProcessingCacheshutdown() throws Exception {
    
    }
}
