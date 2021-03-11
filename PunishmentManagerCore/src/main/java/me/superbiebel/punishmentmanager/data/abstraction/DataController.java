package me.superbiebel.punishmentmanager.data.abstraction;


import java.net.InetAddress;
import java.util.UUID;
import me.superbiebel.punishmentmanager.data.abstraction.service.managers.ServiceManager;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * This is just an abstraction layer to make interaction with services easier. (Façade pattern)
 * Internal use only.
 */
public class DataController {

    private ServiceManager serviceManager;

    public void init(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public void logLoginInfo(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress ip){

        serviceManager.getLoginInfoLoggerService().logLoginInfo(uuid, joinMessage, kickMessage, loginresult, ip);
    }


}
