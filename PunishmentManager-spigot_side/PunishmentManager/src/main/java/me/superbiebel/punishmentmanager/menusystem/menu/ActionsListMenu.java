package me.superbiebel.punishmentmanager.menusystem.menu;

import me.superbiebel.punishmentmanager.menusystem.Menu;
import me.superbiebel.punishmentmanager.menusystem.PlayerMenuUtility;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ActionsListMenu extends Menu {
    public ActionsListMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Action list";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if(e.getRawSlot() == e.getSlot()) {
            e.setCancelled(true);

        }

    }

    @Override
    public void setMenuItems() {

    }
}
