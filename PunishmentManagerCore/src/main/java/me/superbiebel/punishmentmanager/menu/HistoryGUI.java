package me.superbiebel.punishmentmanager.menu;

import me.lucko.helper.menu.Gui;
import org.bukkit.entity.Player;

@Deprecated(forRemoval = true)
public class HistoryGUI extends Gui {


    public HistoryGUI(Player player, int lines, String title) {
        super(player, lines, title);
    }

    @Override
    public void redraw() {

    }
}
