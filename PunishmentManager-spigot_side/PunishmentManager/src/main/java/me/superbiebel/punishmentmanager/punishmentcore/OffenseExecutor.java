package me.superbiebel.punishmentmanager.punishmentcore;

import org.bukkit.entity.Player;

public class OffenseExecutor {
    private Player player;
    private int offenseID;
    public OffenseExecutor(Player player, int offenseID) {
        this.player = player;
        this.offenseID = offenseID;
    }
    public boolean execute() {

        return false;
    }
}
