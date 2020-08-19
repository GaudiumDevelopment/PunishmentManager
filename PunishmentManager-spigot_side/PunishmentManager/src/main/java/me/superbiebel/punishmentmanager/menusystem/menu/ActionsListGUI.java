package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.paginated.PageInfo;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import me.superbiebel.punishmentmanager.Utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ActionsListGUI extends Gui{
    public ActionsListGUI(Player player, int lines, String title) {
        super(player, lines, title);
    }




    @Override
    public void redraw() {
        ItemStack newOffense = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta newOffenseMeta = newOffense.getItemMeta();
        newOffenseMeta.setDisplayName(ColorUtils.translateColorCodes("&4&lNew Offense"));
        newOffense.setItemMeta(newOffenseMeta);
        getSlot(4).setItem(newOffense).bind(e->{
            e.setCancelled(true);

        } );

        ItemStack history = new ItemStack(Material.BOOK, 1);
        ItemMeta historyMeta = history.getItemMeta();
        historyMeta.setDisplayName(ColorUtils.translateColorCodes("&4&lhistory"));
        getSlot(7).setItem(history).bind(e->{
            e.setCancelled(true);

        } );

        }

    }
