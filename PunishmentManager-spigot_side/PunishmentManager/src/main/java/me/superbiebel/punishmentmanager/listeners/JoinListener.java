package me.superbiebel.punishmentmanager.listeners;

import me.superbiebel.punishmentmanager.mysql.MySQL;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JoinListener {

    public JoinListener(PlayerJoinEvent e) {
        handleJoin(e);
    }

    public void handleJoin(PlayerJoinEvent e) {
        try {
            PreparedStatement stmt = MySQL.getDataSource().getConnection().prepareStatement(""); //TODO fill in the sql statement
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}