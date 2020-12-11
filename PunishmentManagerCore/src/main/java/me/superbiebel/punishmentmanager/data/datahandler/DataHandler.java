package me.superbiebel.punishmentmanager.data.datahandler;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.net.InetAddress;
import java.util.UUID;

public interface DataHandler {
    void init() throws Exception;
    void shutdown() throws Exception;
    void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP) throws Exception;
    void insertLeave(UUID uuid, String leaveMessage) throws Exception;
    void insertKick(UUID uuid, String kickreason, String leaveMessage) throws Exception;
}
