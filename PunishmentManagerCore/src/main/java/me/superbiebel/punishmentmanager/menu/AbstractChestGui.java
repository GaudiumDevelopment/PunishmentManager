package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import org.bukkit.entity.Player;

public abstract class AbstractChestGui {
    protected ChestGui gui;
    protected boolean hasBeenConstructed = false;
    protected abstract void open(Player p);
    protected abstract void construct(boolean force, boolean allowlazy);
    
}
