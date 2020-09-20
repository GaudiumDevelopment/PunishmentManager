package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;

public class OffenseListGUI extends PaginatedGui{

    public OffenseListGUI(Function<PaginatedGui, List<Item>> content, Player player, PaginatedGuiBuilder model) {
        super(content, player, model);
    }

    @Override
    public void redraw() {
        super.redraw();
    }
}

