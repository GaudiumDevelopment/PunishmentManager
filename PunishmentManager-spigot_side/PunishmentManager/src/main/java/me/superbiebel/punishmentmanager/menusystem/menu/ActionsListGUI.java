package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;

import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        getSlot(3).setItem(newOffense).bind(e->{
            if (getPlayer().hasPermission("punishmentmanager.offense.offenselist")) {






                PaginatedGuiBuilder model = PaginatedGuiBuilder.create();
                model.title("Punish " + PunishmentManager.getPlayerDataUtility(getPlayer()).getCriminal().getName());
                model.previousPageSlot(48);
                model.nextPageSlot(50);
                Player p = getPlayer();
                OffenseListGUI pgui = new OffenseListGUI(OffenseListGUI -> {
                    List<Item> items = new ArrayList<>();
                    int resultSetSize = 0;
                    ResultSet offenseListGuiItems = null;
                   try {
                       offenseListGuiItems = PunishmentManager.getOffenseListGuiData();
                       offenseListGuiItems.last();
                       resultSetSize = offenseListGuiItems.getRow();
                       offenseListGuiItems.first();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    for(int i = 1; i<=resultSetSize; i++){
                        Item someItem = null;
                        try {
                            offenseListGuiItems.getString(i);
                            someItem = ItemStackBuilder.
                                   of(Material.matchMaterial(offenseListGuiItems.getString("offense_icon")))
                                    .name(offenseListGuiItems.getString("offense_icon")).buildItem().bind(event->{

                                        ExecuteOffenseGUI executeOffenseGUI = new ExecuteOffenseGUI((Player) e.getWhoClicked(), 6, "Punish " + PunishmentManager.getPlayerDataUtility(getPlayer()).getCriminal().getName());
                                        executeOffenseGUI.open();

                                    },ClickType.LEFT).build();
                       } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        items.add(someItem);}
                    return items;
                }, p,model);
                pgui.open();







            } else {
                getPlayer().sendMessage("You do not have permission to do that!");
            }
        }, ClickType.LEFT  );

        ItemStack history = new ItemStack(Material.BOOK, 1);
        ItemMeta historyMeta = history.getItemMeta();
        historyMeta.setDisplayName(ColorUtils.translateColorCodes("&4&lHistory"));
        history.setItemMeta(historyMeta);
        getSlot(5).setItem(history).bind(e->{
            e.setCancelled(true);
            if (getPlayer().hasPermission("punishmentmanager.history")) {
                new HistoryGUI(getPlayer(),6, "History of "+ PunishmentManager.getPlayerDataUtility(getPlayer()).getCriminal().getName()).open();
            } else {
                getPlayer().sendMessage("You do not have permission to do that!");
            }
        }, ClickType.LEFT );

        }


    }
