package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import org.bukkit.entity.Player;

public abstract class AbstractChestGui {
    protected Player cachedPlayer;
    protected ChestGui gui;
    protected boolean hasBeenConstructed = false;
    public abstract void open(Player p);
    public void open() {
        throw new UnsupportedOperationException("Cannot call method from Abstract class");
    }
    public abstract void construct(boolean force, boolean allowlazy);
    
    public void construct(boolean force, boolean allowlazy, Player player) {
        throw new UnsupportedOperationException("Cannot call method from Abstract class");
    }
    public void setCachedPlayer(Player p) {
        throw new UnsupportedOperationException("Cannot call method from Abstract class");
    }
    
}
