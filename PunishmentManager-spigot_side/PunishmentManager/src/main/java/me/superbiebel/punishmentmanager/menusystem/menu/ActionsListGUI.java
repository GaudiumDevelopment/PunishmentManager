package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.menu.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ActionsListGUI extends Gui{
    public ActionsListGUI(Player player, int lines, String title) {
        super(player, lines, title);
    }




    @Override
    public void redraw() {
        ItemStack itemStack = new ItemStack(Material.ACACIA_BOAT, 1);
        getSlot(0).setItem(itemStack);
        getSlot(0).bind(ClickType.LEFT, e->e.getWhoClicked().sendMessage("test"));

        }

    }
