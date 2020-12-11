package me.superbiebel.punishmentmanager.listeners;

import me.lucko.helper.Schedulers;
import me.superbiebel.punishmentmanager.data.managers.DataHandlerManager;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.InetAddress;
import java.util.UUID;

public class JoinListener {
    
    public void handlePreJoin(AsyncPlayerPreLoginEvent e) {
        String possibleKickMessage = e.getKickMessage();
        UUID uuid = e.getUniqueId();
        InetAddress ip = e.getAddress();
        AsyncPlayerPreLoginEvent asyncPlayerPreLoginEvent;
        AsyncPlayerPreLoginEvent.Result result = e.getLoginResult();
        Schedulers.async().run(()->{
            try {
                DataHandlerManager.getDataHandler().insertJoin(uuid,"NOT ALLOWED",possibleKickMessage,result, ip);
            } catch (Exception exception) {
                Log.logException(exception, Log.LogLevel.FATALERROR,true,false,true,true,true);
            }
        });
        
    }
    public void handleJoin(PlayerJoinEvent e) {
        String joinMessage = e.getJoinMessage();
        InetAddress ip = e.getPlayer().getAddress().getAddress();
        UUID uuid =  e.getPlayer().getUniqueId();
        Schedulers.async().run(()->{
            try {
                DataHandlerManager.getDataHandler().insertJoin(uuid,joinMessage,"", AsyncPlayerPreLoginEvent.Result.ALLOWED, ip);
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