package me.superbiebel.punishmentmanager.menu;

import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import me.lucko.helper.menu.scheme.AbstractSchemeMapping;
import me.lucko.helper.menu.scheme.MenuScheme;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated(forRemoval = true)
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

























        /*if (isFirstDraw()) {
            Promise<Void> placeItemsInGuiPromise = Promise.start().thenRunSync(()->{
                List<Item> fetchItems = new ArrayList<>();
                Item fetchItem = ItemStackBuilder.of(Material.ITEM_FRAME).name(ColorUtils.colorize("&6&lFetching data...")).buildItem().build();
                for (int i = 0;i <= 48; i++) {

                    fetchItems.add(fetchItem);
                }
                updateContent(fetchItems);
                super.redraw();

            })

                    .thenRunAsync(()->{
                int resultSetSize = 0;
                ResultSet offenseListGuiItems = null;

                    offenseListGuiItems = DataHandler.FetchOffenseListGuiData(false);
                    if (!(offenseListGuiItems == null)) {
                        try {
                    offenseListGuiItems.last();
                    resultSetSize = offenseListGuiItems.getRow();
                    offenseListGuiItems.first();
                        } catch (SQLException throwable) {
                            throwable.printStackTrace();
                        }



                for(int i = 1; i<=resultSetSize; i++){
                    Item someItem = null;
                    try {
                        someItem = ItemStackBuilder.
                                    of(Material.matchMaterial(offenseListGuiItems.getString("offense_icon")))
                                    .name(offenseListGuiItems.getString("offense_icon")).buildItem().bind(event->{

                                        ExecuteOffenseGUI executeOffenseGUI = new ExecuteOffenseGUI(this.player);
                                        executeOffenseGUI.open();

                                    }, ClickType.LEFT).build();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    content.add(someItem);}
            } else {
                        Item itemsNotFoundItem = ItemStackBuilder.of(Material.ITEM_FRAME).name(ColorUtils.colorize("&4&lCould not fetch items or there are no items in here")).buildItem().build();
                        content.add(itemsNotFoundItem);
                    }
                }).thenRunSync(()-> {

                updateContent(content);
                super.redraw();

            });

        }*/


        super.redraw();
    }
}

