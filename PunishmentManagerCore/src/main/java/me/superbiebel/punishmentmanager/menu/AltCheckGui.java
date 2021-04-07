package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane.Priority;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.lucko.helper.metadata.Metadata;
import me.superbiebel.punishmentmanager.data.DATAKEYS;
import me.superbiebel.punishmentmanager.menu.abstraction.AbstractChestGui;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AltCheckGui extends AbstractChestGui {

    ItemStack backButtonItemstack;
    GuiItem backButtonItem;
    StaticPane buttonPane;

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


        backButtonItemstack = new ItemStack(Material.BARRIER);
        backButtonItem = new GuiItem(backButtonItemstack);
        backButtonItem.setAction(event->{
            event.setCancelled(true);
            ActionListGui actionListGui = new ActionListGui();
            actionListGui.construct(false, false,(Player) event.getWhoClicked());
            actionListGui.open();
        });
        backButtonItemstack.getItemMeta().setDisplayName(ColorUtils.colorize("&c&lBack"));


        buttonPane = new StaticPane(0,5,9,1, Priority.HIGHEST);
        buttonPane.addItem(backButtonItem,4,0);
        super.gui.addPane(buttonPane);
    }
}
