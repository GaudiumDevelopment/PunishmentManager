package me.superbiebel.punishmentmanager.listeners;

import java.net.InetAddress;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import me.lucko.helper.Events;
import me.lucko.helper.Schedulers;
import me.lucko.helper.event.SingleSubscription;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginInfoLogger implements AbstractListener{
    /*
    If a player join this is what will happen:
    - AsyncPlayerPreLoginEvent will be called and every loginattempt that doesn't result in a successful join will be logged here.
    - PlayerJoinEvent will be called when a loginattempt is successful and will log all the data for that loginattempt.
     */
    @Getter
    public static final LoginInfoLogger INSTANCE = new LoginInfoLogger();

    public SingleSubscription<AsyncPlayerPreLoginEvent> preJoinListener;
    public SingleSubscription<PlayerJoinEvent> successfulJoinListener;


    @Override
    public void init() {
        preJoinListener = Events.subscribe(AsyncPlayerPreLoginEvent.class, EventPriority.MONITOR).handler(INSTANCE::handleJoin);
        successfulJoinListener = Events.subscribe(PlayerJoinEvent.class, EventPriority.MONITOR).handler(INSTANCE::handleJoin);
    }

    @Override
    public void shutdown() {
        preJoinListener.close();
        successfulJoinListener.close();
    }

    public void handleJoin(Event e) {
        if (e instanceof AsyncPlayerPreLoginEvent && ((AsyncPlayerPreLoginEvent) e).getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) {
                handlePreJoin((AsyncPlayerPreLoginEvent) e);
        } else if (e instanceof PlayerJoinEvent) {
            handleSuccessfulJoin((PlayerJoinEvent) e);
        }

    }

    private void handlePreJoin(AsyncPlayerPreLoginEvent e) {
        String possibleKickMessage = e.getKickMessage();
        UUID uuid = e.getUniqueId();
        InetAddress ip = e.getAddress();
        AsyncPlayerPreLoginEvent.Result result = e.getLoginResult();
        Schedulers.async().run(()->{
            try {
                PunishmentManager.getServiceManager().getLoginInfoLoggerService().logLoginInfo(uuid,"NOT ALLOWED",possibleKickMessage,result, ip);
            } catch (Exception exception) {
                Log.logException(exception, Log.LogLevel.FATALERROR,true,false,true,true,true);
            }
        });
        
    }
    private void handleSuccessfulJoin(PlayerJoinEvent e) {
        String joinMessage = e.getJoinMessage();
        InetAddress ip = Objects.requireNonNull(e.getPlayer().getAddress()).getAddress();
        UUID uuid =  e.getPlayer().getUniqueId();
        Schedulers.async().run(()->{
            try {
                PunishmentManager.getServiceManager().getLoginInfoLoggerService().logLoginInfo(uuid,joinMessage,"", AsyncPlayerPreLoginEvent.Result.ALLOWED, ip);
            } catch (Exception exception) {
                Log.logException(exception, Log.LogLevel.FATALERROR,true,false,true,true,true);
            }
        });
    }


}