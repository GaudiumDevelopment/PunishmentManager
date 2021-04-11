package me.superbiebel.punishmentmanager.data.abstraction.depracated;

import java.util.List;
import me.superbiebel.punishmentmanager.data.dataobjects.OffenseKey;

@Deprecated(forRemoval = true)
public interface Cache {
    /**
     * @throws Exception concludes all the exception that might be thrown by different caches.
     */
    void init() throws Exception;
    /**
     * @throws Exception concludes all the exception that might be thrown by different caches.
     */
    void transfer() throws Exception;
    /**
     * @throws Exception concludes all the exception that might be thrown by different caches.
     */
    void close() throws Exception;
    
    <T> void putCachedInventory(T gui) throws Exception;
    
    <T> T getCachedInventory(String key) throws Exception;
    
    List<OffenseKey> getAllOffenseKeys();
}
