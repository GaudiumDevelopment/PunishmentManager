package me.superbiebel.punishmentmanager.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private Player criminal;
    private Player owner;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }
    public Player getCriminal() {return criminal;}
    public void setCriminal(Player criminal) {this.criminal = criminal;}
}

