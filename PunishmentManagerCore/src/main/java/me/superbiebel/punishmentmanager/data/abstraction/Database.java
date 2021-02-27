package me.superbiebel.punishmentmanager.data.abstraction;

import me.superbiebel.punishmentmanager.data.dataObjects.OffenseKey;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

@Deprecated(forRemoval = true)
public interface Database {
    void init() throws Exception;
    void shutdown() throws Exception;
    void fetchOffenselistGUIData() throws Exception;
    void fetchHistoryGUIData() throws Exception;
    List<UUID> fetchBannedPlayers() throws Exception;
    void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP) throws Exception;
    void insertIp(String ip) throws Exception;
    void insertLeave(UUID uuid, String leaveMessage) throws Exception;
    void insertKick(UUID uuid, String kickreason, String leaveMessage) throws Exception;
    List<OffenseKey> getAllOffenseKeys();
}
