package me.superbiebel.punishmentmanager.data.abstraction;

import me.superbiebel.punishmentmanager.data.AVAILABILITY;
import me.superbiebel.punishmentmanager.data.dataObjects.HistoryRecord;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

public interface DataHandler {
    void init() throws Exception;
    void shutdown() throws Exception;
    void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP) throws Exception;
    void insertLeave(UUID uuid, String leaveMessage) throws Exception;
    void insertKick(UUID uuid, String kickreason, String leaveMessage) throws Exception;
    /**
     * @returns null if not found otherwise returns cached inv
     */
    <T> T getCachedInventory(String key);
    <T> void putCachedInventory(String key, AVAILABILITY availability, T inventory);
    List<HistoryRecord> getHistory(UUID uuid);
    
}
