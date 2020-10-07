package me.superbiebel.punishmentmanager.listeners;

import me.lucko.helper.Schedulers;
import me.superbiebel.punishmentmanager.data.MySQL;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JoinListener {

    public JoinListener(AsyncPlayerPreLoginEvent e) {
        handleJoin(e);
    }

    public void handleJoin(AsyncPlayerPreLoginEvent e) {
            Connection con = null;
            PreparedStatement joinStmt = null;
            PreparedStatement ipStmt = null;

            String uuid = e.getUniqueId().toString();
            String loginResult = e.getLoginResult().name();
            Long currentTime = System.currentTimeMillis();
            String ip = e.getAddress().toString().replaceAll("/","");
        try {
            con = MySQL.getMysqlDataSource().getConnection();

            joinStmt = con.prepareStatement("INSERT INTO player_joins (uuid, join_date, result, ip) VALUES (?,?,?,?);");
            joinStmt.setString(1,uuid);
            joinStmt.setLong(2,currentTime);
            joinStmt.setString(3,loginResult);
            joinStmt.setString(4,ip);


            ipStmt = con.prepareStatement("INSERT INTO player_IP (ip, uuid, creation_date) SELECT ?,?,? FROM DUAL WHERE NOT EXISTS (SELECT ip FROM player_ip WHERE ip=?AND uuid=?);");
            ipStmt.setString(1,ip);
            ipStmt.setString(2,uuid);
            ipStmt.setLong(3, currentTime);
            ipStmt.setString(4,ip);
            ipStmt.setString(5,uuid);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Connection finalCon = con;
        PreparedStatement finalStmt = joinStmt;
        PreparedStatement finalIpStmt = ipStmt;
        Schedulers.async().run(()->{
            try {
                finalStmt.executeUpdate();
                finalIpStmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }
}