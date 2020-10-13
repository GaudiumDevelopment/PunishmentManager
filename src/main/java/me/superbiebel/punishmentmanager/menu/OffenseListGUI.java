package me.superbiebel.punishmentmanager.menu;

import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OffenseListGUI extends PaginatedGui{
    private List<Item> content = new ArrayList<>();
    private Player player;
    private PaginatedGuiBuilder model;

    public OffenseListGUI(Player player) {
        super(paginatedGui -> {
            List<Item> items = new ArrayList<>();
            return items;
        }, player,PaginatedGuiBuilder.create().title("constructor test"));
        this.player = player;


    }

    @Override
    public void redraw() {
        if (isFirstDraw()) {
            content.add(ItemStackBuilder.of(Material.ACACIA_BOAT).buildItem().build());
            this.updateContent(content);

        super.redraw();}


        super.redraw();
    }
}

