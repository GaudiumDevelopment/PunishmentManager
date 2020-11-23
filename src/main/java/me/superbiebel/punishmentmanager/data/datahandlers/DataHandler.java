package me.superbiebel.punishmentmanager.data.datahandlers;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public interface DataHandler {
    void init() throws Exception;
    void close() throws Exception;
    void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult) throws Exception;
    void insertLeave(UUID uuid, String leaveMessage) throws Exception;
    void insertKick(UUID uuid, String kickMessage) throws Exception;
}
