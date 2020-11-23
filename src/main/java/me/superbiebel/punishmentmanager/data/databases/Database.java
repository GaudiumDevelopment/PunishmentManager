package me.superbiebel.punishmentmanager.data.databases;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public interface Database {
    void init() throws Exception;
    void close() throws Exception;
    void fetchOffenselistGUIData() throws Exception;
    void fetchHistoryGUIData() throws Exception;
    ArrayList fetchBannedPlayers() throws Exception;
    void insertJoin(Player p) throws Exception;
    void insertIp(String ip) throws Exception;
    void insertQuit(Player p) throws Exception;
    void insertKick(Player p, String reason) throws Exception;

}
