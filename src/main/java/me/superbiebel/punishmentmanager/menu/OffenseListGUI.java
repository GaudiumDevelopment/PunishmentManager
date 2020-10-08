package me.superbiebel.punishmentmanager.menu;

import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import me.superbiebel.punishmentmanager.data.FetchData;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
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
         ResultSet guiData = FetchData.FetchOffenseListGuiData();
        super.redraw();}


        super.redraw();
    }
}

