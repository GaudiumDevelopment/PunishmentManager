package me.superbiebel.punishmentmanager.menu;

import java.util.Objects;
import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.Item;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

@Deprecated(forRemoval = true)
public class ActionsListGUI extends Gui{

    public ActionsListGUI(Player player) {
        super(player, 3, "Choose action for ");
    }





    @Override
    public void redraw() {
       /* ItemStack newOffense = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta newOffenseMeta = newOffense.getItemMeta();
        newOffenseMeta.setDisplayName(ColorUtils.colorize("&4&lNew Offense"));
        newOffense.setItemMeta(newOffenseMeta);*/
        Player p = getPlayer();
        Item newOffense = ItemStackBuilder.of(Material.IRON_AXE).name(ColorUtils.colorize("&4&lNew Offense")).buildConsumer(ClickType.LEFT,e->{


            if (getPlayer().hasPermission("punishmentmanager.offense.offenselist")) {
                OffenseListGUI offenseListGUI =  new OffenseListGUI(p);
                offenseListGUI.open();
                /*OffenseListGUI offenseListGUI = (OffenseListGUI) model.title("Punish " + Metadata.provideForPlayer(p).get(DataUtility.getCriminalKey()).get().getName()).previousPageSlot(48).nextPageSlot(50).build(p, gui-> {
                    List<Item> items = new ArrayList<>();
                    return items;
                });
                offenseListGUI.open();*/

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
                })
                pgui.open();*/
            } else {
                getPlayer().sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
            }
        });
        setItem(13,newOffense);
        Item history = ItemStackBuilder.of(Material.BOOK).name(ColorUtils.colorize("&4&lHistory")).buildConsumer(ClickType.LEFT,e->{
            if (getPlayer().hasPermission("punishmentmanager.history.gui")) {
              //  new HistoryGUI(getPlayer(),6, "History of ");
            } else {
                getPlayer().sendMessage(ColorUtils.colorize(Objects.requireNonNull(PunishmentManager.giveConfig().getString("messages.noPermissionMessage"))));
            }
        });
        setItem(11,history);


        ItemStack accountInfoSkullItemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta accountInfoSkullItemStackItemMeta = (SkullMeta) accountInfoSkullItemStack.getItemMeta();
        accountInfoSkullItemStack.setItemMeta(accountInfoSkullItemStackItemMeta);
        Item accountInfoSkull = ItemStackBuilder.of(accountInfoSkullItemStack).buildItem().build();
        Item accountInfo = ItemStackBuilder.of(Material.PLAYER_HEAD).name(ColorUtils.colorize("&4&lAlts")).buildItem().build();
        setItem(15, accountInfo);
        }



    }
