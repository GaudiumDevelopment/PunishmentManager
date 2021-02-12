package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import me.lucko.helper.metadata.Metadata;
import me.superbiebel.punishmentmanager.data.DATAKEYS;
import org.bukkit.entity.Player;

public class AltCheckGui extends AbstractChestGui{
    @Override
    public void open(Player p) {
        cachedPlayer = p;
        super.gui.show(cachedPlayer);
    }
    
    @Override
    public void construct(boolean force, boolean allowlazy) {
        throw new UnsupportedOperationException("Altcheckgui needs a player to construct, used the wrong method!");
    }

    @Override
    public void construct(boolean force, boolean allowlazy, Player player) {
        super.gui = new ChestGui(6,"Alts of " + Metadata.provideForPlayer(player).get(DATAKEYS.CRIMINAL_KEY)
                .orElseThrow(()-> new NullPointerException("Could not find the player in the map to alt check for!"))
                .getName());
    }
}
