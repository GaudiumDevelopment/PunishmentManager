package me.superbiebel.punishmentmanager.data.layers;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.UUID;

public interface Database {
    void init() throws Exception;
    void shutdown() throws Exception;
    void fetchOffenselistGUIData() throws Exception;
    void fetchHistoryGUIData() throws Exception;
    ArrayList fetchBannedPlayers() throws Exception;
    void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP) throws Exception;
    void insertIp(String ip) throws Exception;
    void insertLeave(UUID uuid, String leaveMessage) throws Exception;
    void insertKick(UUID uuid, String kickreason, String leaveMessage) throws Exception;
}
