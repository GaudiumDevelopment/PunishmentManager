package me.superbiebel.defaultmysqldatabase;

import java.net.InetAddress;
import java.util.UUID;
import me.superbiebel.punishmentmanager.data.abstraction.service.ServiceType;
import me.superbiebel.punishmentmanager.data.abstraction.service.services.LoginInfoLoggerService;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class LoginInfoLoggerMysql implements LoginInfoLoggerService {
    @Override
    public void startup(boolean forceStart) throws Exception {

    }

    @Override
    public void shutdown() throws Exception {

    }

    @Override
    public void kill() throws Exception {

    }

    @Override
    public ServiceType getType() {
        return ServiceType.loginInfoLoggerService;
    }

    @Override
    public boolean isInit() {
        return false;
    }

    @Override
    public void logLoginInfo(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP) {

    }
}
