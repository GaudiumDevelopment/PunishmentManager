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

        Material type;
        ItemStack newOffense = new ItemStack(Material.IRON_AXE, 1);
        ItemStack history = new ItemStack(Material.BOOK, 1);
        getSlot(11).setItem(newOffense);
        getSlot(15).setItem(history);

        }

    }
