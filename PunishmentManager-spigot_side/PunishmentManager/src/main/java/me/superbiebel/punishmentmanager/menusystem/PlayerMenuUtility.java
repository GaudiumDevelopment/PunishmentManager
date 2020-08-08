package me.superbiebel.punishmentmanager.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private Player victim;
    private Player owner;
    //store the player that will be killed so we can access him in the next menu

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }
    public Player getVictim() {return victim;}

    public void setVictim(Player victim) {this.victim = victim;}
}

