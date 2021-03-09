package me.superbiebel.punishmentmanager.data.abstraction.service.services;

import java.net.InetAddress;
import java.util.UUID;
import me.superbiebel.punishmentmanager.data.abstraction.service.Service;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public interface LoginInfoLoggerService extends Service {

    public static final String SERVICE_NAME = "LoginInfoLoggerService";
    void logLoginInfo(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP);
}
