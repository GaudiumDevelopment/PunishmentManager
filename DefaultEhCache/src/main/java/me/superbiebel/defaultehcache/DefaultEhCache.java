package me.superbiebel.defaultehcache;

import java.util.List;
import me.superbiebel.punishmentmanager.data.abstraction.depracated.Cache;
import me.superbiebel.punishmentmanager.data.dataobjects.OffenseKey;

public class DefaultEhCache implements Cache {
    
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
    public List<OffenseKey> getAllOffenseKeys() {
        return null;
    }
}
