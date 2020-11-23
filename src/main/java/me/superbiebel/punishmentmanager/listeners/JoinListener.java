package me.superbiebel.punishmentmanager.listeners;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class JoinListener {

    public JoinListener(AsyncPlayerPreLoginEvent e) {
        handleJoin(e);
    }

    public void handleJoin(AsyncPlayerPreLoginEvent e) {
        //TODO: change this to the protocol from the databse interface
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