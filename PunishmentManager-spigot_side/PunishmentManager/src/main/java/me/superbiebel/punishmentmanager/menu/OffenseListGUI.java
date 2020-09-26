package me.superbiebel.punishmentmanager.menu;

import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OffenseListGUI extends PaginatedGui{
    private List<Item> content = new ArrayList<>();
    private Player player;
    private PaginatedGuiBuilder model;

    public OffenseListGUI(Function<PaginatedGui, List<Item>> content, Player player, PaginatedGuiBuilder model) {
        super(content, player, model);
        this.player = player;
        this.model = model;

    }

    @Override
    public void redraw() {
        if (isFirstDraw()) {
        Item testItem = ItemStackBuilder.of(Material.ACACIA_BOAT).buildItem().build();
        for (int i = 0; i <=50; i++) {
        this.content.add(testItem);}
        updateContent(this.content);
        super.redraw();}


        super.redraw();
    }
}

