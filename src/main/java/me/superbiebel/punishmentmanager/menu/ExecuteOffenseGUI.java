package me.superbiebel.punishmentmanager.menu;

import me.lucko.helper.menu.Gui;
import me.lucko.helper.metadata.Metadata;
import me.superbiebel.punishmentmanager.data.DataUtility;
import org.bukkit.entity.Player;

public class ExecuteOffenseGUI extends Gui{
    public ExecuteOffenseGUI(Player player) {
        super(player, 6, "punish: " + Metadata.provideForPlayer(player).get(DataUtility.CRIMINAL_KEY).get().getName());
    }

    @Override
    public void redraw() {

    }


}
