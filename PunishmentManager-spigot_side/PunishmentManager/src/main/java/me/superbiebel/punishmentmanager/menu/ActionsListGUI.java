package me.superbiebel.punishmentmanager.menu;

import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import me.lucko.helper.metadata.Metadata;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import me.superbiebel.punishmentmanager.utils.DataUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
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
        newOffenseMeta.setDisplayName(ColorUtils.colorize("&4&lNew Offense"));
        newOffense.setItemMeta(newOffenseMeta);
        Player p = getPlayer();
        getSlot(3).setItem(newOffense).bind(e->{
            if (getPlayer().hasPermission("punishmentmanager.offense.offenselist")) {

                PaginatedGuiBuilder model = PaginatedGuiBuilder.create();
                model.title("Punish " + Metadata.provideForPlayer(p).get(DataUtility.getCriminalKey()).get().getName()).previousPageSlot(48).nextPageSlot(50).build(p,gui-> {
                    List<Item> items = new ArrayList<>();
                    return items;
                }).open();

                /*OffenseListGUI pgui = new OffenseListGUI(OffenseListGUI -> {
                    List<Item> items = new ArrayList<>();
                    return items;
                }
                    /*List<Item> items = new ArrayList<>();
                    int resultSetSize = 0;
                    ResultSet offenseListGuiItems = null;
                   try {
                       offenseListGuiItems = FetchData.FetchOffenseListGuiData();
                       offenseListGuiItems.last();
                       resultSetSize = offenseListGuiItems.getRow();
                       offenseListGuiItems.first();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (InterruptedException interruptedException) {
                       interruptedException.printStackTrace();
                   } catch (ExecutionException executionException) {
                       executionException.printStackTrace();
                   }
                    for(int i = 1; i<=resultSetSize; i++){
                        Item someItem = null;
                        try {
                            offenseListGuiItems.getString(i);
                            someItem = ItemStackBuilder.
                                   of(Material.matchMaterial(offenseListGuiItems.getString("offense_icon")))
                                    .name(offenseListGuiItems.getString("offense_icon")).buildItem().bind(event->{

                                        ExecuteOffenseGUI executeOffenseGUI = new ExecuteOffenseGUI((Player) e.getWhoClicked(), 6, "Punish " + Metadata.provideForPlayer(p).get(DataUtility.getCriminalKey()).get().getName());
                                        executeOffenseGUI.open();

                                    },ClickType.LEFT).build();
                       } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        items.add(someItem);}
                    return items;
                }, p,model);
                pgui.open();*/







            } else {
                getPlayer().sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
            }
        }, ClickType.LEFT  );

        ItemStack history = new ItemStack(Material.BOOK, 1);
        ItemMeta historyMeta = history.getItemMeta();
        historyMeta.setDisplayName(ColorUtils.colorize("&4&lHistory"));
        history.setItemMeta(historyMeta);
        getSlot(5).setItem(history).bind(e->{
            e.setCancelled(true);
            if (getPlayer().hasPermission("punishmentmanager.history.gui")) {
                new HistoryGUI(getPlayer(),6, "History of "+ Metadata.provideForPlayer(p).get(DataUtility.getCriminalKey()).get().getName()).open();
            } else {
                getPlayer().sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
            }
        }, ClickType.LEFT );

        }


    }
