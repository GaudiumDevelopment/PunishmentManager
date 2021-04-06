package me.superbiebel.punishmentmanager.data.abstraction.depracated;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;
import me.superbiebel.punishmentmanager.data.AVAILABILITY;
import me.superbiebel.punishmentmanager.data.dataObjects.HistoryRecord;
import me.superbiebel.punishmentmanager.data.dataObjects.OffenseKey;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

@Deprecated(forRemoval = true)
public interface DataHandler {
    void init() throws Exception;
    void shutdown() throws Exception;
    void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP) throws Exception;
    void insertLeave(UUID uuid, String leaveMessage) throws Exception;
    void insertKick(UUID uuid, String kickreason, String leaveMessage) throws Exception;
    <T> T getCachedInventory(String key);
    <T> void putCachedInventory(String key, AVAILABILITY availability, T inventory);
    List<HistoryRecord> getHistory(UUID uuid,boolean nocache);
    List<OffenseKey> getAllOffenseKeys();
}
