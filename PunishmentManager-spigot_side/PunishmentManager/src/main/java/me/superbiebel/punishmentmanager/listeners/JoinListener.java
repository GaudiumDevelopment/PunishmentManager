package me.superbiebel.punishmentmanager.listeners;

import me.superbiebel.punishmentmanager.mysql.MySQL;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JoinListener {

    public JoinListener(AsyncPlayerPreLoginEvent e) {
        handleJoin(e);
    }

    public void handleJoin(AsyncPlayerPreLoginEvent e) {
        Connection con = null;
        try {
            con = MySQL.getDataSource().getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO player_joins (uuid, join_date, result) VALUES (?,?,?);");
            stmt.setString(1,e.getUniqueId().toString());
            stmt.setLong(2,System.currentTimeMillis());
            stmt.setString(3, e.getLoginResult().name());
            Log.debug(e.getUniqueId().toString());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}