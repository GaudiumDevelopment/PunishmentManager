package me.superbiebel.punishmentmanager.punishmentcore;

import org.bukkit.entity.Player;

public class OffenseExecutor {
    private Player executor;
    private Player criminal;
    private int offenseID;

    public OffenseExecutor(final Player executor, final int offenseID) {
        this.executor = executor;
        this.offenseID = offenseID;
    }

    public boolean execute() {
        return false;
    }


    public void loadScript() {

    }
}