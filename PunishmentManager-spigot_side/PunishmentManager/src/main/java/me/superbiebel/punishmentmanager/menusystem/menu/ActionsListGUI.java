package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.menu.Gui;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.Utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ActionsListGUI extends Gui{
    public ActionsListGUI(Player player, int lines, String title) {
        super(player, lines, title);
    }


    private static OffenseListGUI pguiInstance;



    @Override
    public void redraw() {
        ItemStack newOffense = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta newOffenseMeta = newOffense.getItemMeta();
        newOffenseMeta.setDisplayName(ColorUtils.translateColorCodes("&4&lNew Offense"));
        newOffense.setItemMeta(newOffenseMeta);
        getSlot(3).setItem(newOffense).bind(e->{
            e.setCancelled(true);
            if (getPlayer().hasPermission("punishmentmanager.offense.offenselist")) {
                new OffenseListGUI(this.getPlayer(), 6, "Choose an offense").open();
            } else {
                getPlayer().sendMessage("You do not have permission to do that!");

            }

        }, ClickType.LEFT  );

        ItemStack history = new ItemStack(Material.BOOK, 1);
        ItemMeta historyMeta = history.getItemMeta();
        historyMeta.setDisplayName(ColorUtils.translateColorCodes("&4&lhistory"));
        history.setItemMeta(historyMeta);
        getSlot(5).setItem(history).bind(e->{
            e.setCancelled(true);
            if (getPlayer().hasPermission("punishmentmanager.history")) {
                new HistoryGUI(getPlayer(),6, "History of "+ PunishmentManager.getPlayerMenuUtility(getPlayer()).getCriminal().getName()).open();
            } else {
                getPlayer().sendMessage("You do not have permission to do that!");
            }
        }, ClickType.LEFT );

        }

    }
