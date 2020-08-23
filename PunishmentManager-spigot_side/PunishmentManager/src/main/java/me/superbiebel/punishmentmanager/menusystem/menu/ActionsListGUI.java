package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PageInfo;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import me.lucko.helper.promise.Promise;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.Utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


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
                Promise<Void> placeItems = Promise.start().thenRunAsync(()->{
                PaginatedGuiBuilder model = PaginatedGuiBuilder.create();
                model.title("test");
                model.previousPageSlot(48);
                model.nextPageSlot(50);
                Player p = getPlayer();
                OffenseListGUI pgui = new OffenseListGUI(OffenseListGUI -> {
                    List<Item> items = new ArrayList<>();
                    for(int i=0;i<=100;i++){
                    Item someItem = ItemStackBuilder.of(Material.STONE).name("meow").buildItem().build();
                    items.add(someItem);}

                    return items;
                }, p,model);

                });









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
                new HistoryGUI(getPlayer(),6, "History of "+ PunishmentManager.getPlayerMenuUtility(getPlayer()).getCriminal().getName()).open();
            } else {
                getPlayer().sendMessage("You do not have permission to do that!");
            }
        }, ClickType.LEFT );

        }


    }
