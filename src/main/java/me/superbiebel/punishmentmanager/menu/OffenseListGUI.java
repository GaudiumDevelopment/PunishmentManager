package me.superbiebel.punishmentmanager.menu;

import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import me.lucko.helper.menu.scheme.AbstractSchemeMapping;
import me.lucko.helper.menu.scheme.MenuScheme;
import me.lucko.helper.promise.Promise;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OffenseListGUI extends PaginatedGui{
    private List<Item> content = new ArrayList<>();
    private Player player;
    private PaginatedGuiBuilder model;

    public OffenseListGUI(Player player) {
        super(paginatedGui -> new ArrayList<>(), player, layout());
        this.player = player;


    }


    private static PaginatedGuiBuilder layout() {
       PaginatedGuiBuilder model = PaginatedGuiBuilder.create();
       model.title("test");
       List<Integer> itemSlots = new ArrayList<>();
       for (int i = 0; i < 53;i++){
           if (i < 45) {
           itemSlots.add(i);}
       }
       model.itemSlots(itemSlots);
       Map<Integer,Item> schemeMap = new HashMap();
       model.scheme(new MenuScheme(AbstractSchemeMapping.of(schemeMap)));
        return model;
    }

    @Override
    public void redraw() {
        if (isFirstDraw()) {
            Promise<Void> placeItemsInGuiPromise = Promise.start().thenRunSync(()->{
               for (int i = 0; i < 100; i++) {

                   Item item = ItemStackBuilder.of(Material.ACACIA_BOAT).name(ColorUtils.colorize("&#eda215&lFetching data....")).buildItem().build();
                   content.add(item);

               }


            });

            this.updateContent(content);

        super.redraw();}


        super.redraw();
    }
}

