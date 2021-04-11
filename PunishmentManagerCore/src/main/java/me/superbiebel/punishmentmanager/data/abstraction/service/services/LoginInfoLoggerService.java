package me.superbiebel.punishmentmanager.data.abstraction.service.services;

import java.net.InetAddress;
import java.util.UUID;
import me.superbiebel.punishmentmanager.data.abstraction.service.Service;
import me.superbiebel.punishmentmanager.data.abstraction.service.ServiceType;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public interface LoginInfoLoggerService extends Service {

    ServiceType SERVICE_NAME = ServiceType.loginInfoLoggerService;
    void logLoginInfo(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress ip) throws Exception;
}
