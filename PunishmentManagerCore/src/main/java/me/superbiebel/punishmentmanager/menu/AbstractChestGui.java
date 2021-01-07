package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import org.bukkit.entity.Player;

public abstract class AbstractChestGui {
    protected Player cachedPlayer;
    protected ChestGui gui;
    protected boolean hasBeenConstructed = false;
    protected abstract void open(Player p);
    public void open() {
        throw new IllegalStateException("Cannot call method from Abstract class");
    }
    protected abstract void construct(boolean force, boolean allowlazy);
    
    protected void construct(boolean force, boolean allowlazy, Player player) {
        throw new IllegalStateException("Cannot call method from Abstract class");
    }
    public void setCachedPlayer(Player p) {
        throw new IllegalStateException("Cannot call method from Abstract class");
    }
    
}
