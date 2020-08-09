package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.Events;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.Slot;
import me.lucko.helper.terminable.Terminable;
import me.lucko.helper.terminable.TerminableConsumer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;

public class ActionslistMenuWithHelper extends Gui{
    public ActionslistMenuWithHelper(Player player, int lines, String title) {
        super(player, lines, title);
    }




    @Override
    public void redraw() {
        Material type;
        ItemStack itemStack = new ItemStack(Material.ACACIA_BOAT, 1);
        getSlot(0).setItem(itemStack);
        getSlot(0).bind(ClickType.LEFT, e->e.getWhoClicked().sendMessage("test"));

        }

    }
