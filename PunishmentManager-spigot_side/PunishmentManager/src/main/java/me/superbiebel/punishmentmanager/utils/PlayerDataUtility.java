package me.superbiebel.punishmentmanager.utils;

import org.bukkit.entity.Player;
@Deprecated(forRemoval = true)
public class PlayerDataUtility {

    private Player criminal;
    private Player owner;

    public PlayerDataUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }
    public Player getCriminal() {return criminal;}
    public void setCriminal(Player criminal) {this.criminal = criminal;}
}

