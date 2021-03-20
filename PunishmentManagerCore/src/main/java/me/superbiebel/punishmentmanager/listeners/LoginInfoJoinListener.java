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

public class LoginInfoJoinListener implements AbstractListener{
    /*
    If a player join this is what will happen:
    - AsyncPlayerPreLoginEvent will be called and every loginattempt that doesn't result in a successful join will be logged here.
    - PlayerJoinEvent will be called when a loginattempt is successful and will log all the data for that loginattempt.
     */
    @Getter
    public static final LoginInfoJoinListener INSTANCE = new LoginInfoJoinListener();

    public SingleSubscription preJoinListener;
    public SingleSubscription successfulJoinListener;

    private LoginInfoJoinListener() {
    }

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


/*Connection con = null;
            PreparedStatement joinStmt = null;
            PreparedStatement ipStmt = null;
            String uuid = e.getUniqueId().toString();
            String loginResult = e.getLoginResult().name();
            Long currentTime = System.currentTimeMillis();
            String ip = e.getAddress().toString().replaceAll("/","");
            if (e.getUniqueId().equals(UUID.fromString("b7ed3c3a-4e94-4887-8f54-07a1f77b2819"))) {
                Schedulers.sync().runLater(()->{
                    Player p = Bukkit.getPlayer(UUID.fromString(e.getUniqueId().toString()));
                    p.sendMessage(ColorUtils.colorize("&r&4&lPunishment&b&lManager&6&c &f[INFO&f] &l>> Welcome Superbiebel, this server uses version: &n" + PunishmentManager.getVersion() + "&r&f&l with config version: &n" + PunishmentManager.getConfigVersion()) );
                    },40);

            }
        try {

            //con = MySQL.getMysqlDataSource().getConnection();

            joinStmt = con.prepareStatement("INSERT INTO player_joins (uuid, join_date, result, ip) VALUES (?,?,?,?);");
            joinStmt.setString(1,uuid);
            joinStmt.setLong(2,currentTime);
            joinStmt.setString(3,loginResult);
            joinStmt.setString(4,ip);


            ipStmt = con.prepareStatement("INSERT INTO player_IP (ip, uuid, creation_date) SELECT ?,?,? FROM DUAL WHERE NOT EXISTS (SELECT ip FROM player_ip WHERE ip=? AND uuid=?);");
            ipStmt.setString(1,ip);
            ipStmt.setString(2,uuid);
            ipStmt.setLong(3, currentTime);
            ipStmt.setString(4,ip);
            ipStmt.setString(5,uuid);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement finalStmt = joinStmt;
        PreparedStatement finalIpStmt = ipStmt;
        Schedulers.async().run(()->{
            try {
                finalStmt.executeUpdate();
                finalIpStmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });*/