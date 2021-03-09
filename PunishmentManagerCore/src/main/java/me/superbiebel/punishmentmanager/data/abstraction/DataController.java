package me.superbiebel.punishmentmanager.data.abstraction;


import java.net.InetAddress;
import java.util.List;
import java.util.UUID;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.AVAILABILITY;
import me.superbiebel.punishmentmanager.data.dataObjects.HistoryRecord;
import me.superbiebel.punishmentmanager.data.dataObjects.OffenseKey;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * This is just an abstraction layer to make interaction with services easier. (Façade pattern)
 * Internal use only.
 */
public class DataController {
    void logLoginInfo(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP){

        PunishmentManager.getServiceManager().getLoginInfoLoggerService().logLoginInfo(uuid, joinMessage, kickMessage, loginresult, IP);
    }

    public void insertLeave(UUID uuid, String leaveMessage) throws Exception {

    }

    public void insertKick(UUID uuid, String kickreason, String leaveMessage) throws Exception {

    }

    public <T> T getCachedInventory(String key) {
        return null;
    }

    public <T> void putCachedInventory(String key, AVAILABILITY availability, T inventory) {

    }

    public List<HistoryRecord> getHistory(UUID uuid, boolean nocache) {
        return null;
    }

    public List<OffenseKey> getAllOffenseKeys() {
        return null;
    }

}
