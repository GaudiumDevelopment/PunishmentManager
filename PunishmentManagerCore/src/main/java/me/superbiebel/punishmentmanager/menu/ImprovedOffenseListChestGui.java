package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ImprovedOffenseListChestGui extends AbstractChestGui {
    public ImprovedOffenseListChestGui() {
        super();
    }
    
    @Override
    public void open(Player p) {
        super.gui.show(p);
    }
    
    @Override
    public void construct(boolean force, boolean allowlazy) {
        //ThreadContext threadContext = allowlazy ? ThreadContext.ASYNC : ThreadContext.SYNC;
        //Promise<Void> constructpanePromise = Promise.empty().thenRun(threadContext,()->{ });
        long firstTime = System.currentTimeMillis();
        super.gui = new ChestGui(6,"Choose an offense");
        List<GuiItem> guiItemList = new ArrayList<>();
        GuiItem guiItem = new GuiItem(new ItemStack(Material.ACACIA_BOAT));
        for (int i = 0;i<100;i++){
            guiItemList.add(guiItem);
        }
        PaginatedPane pagedpane = new PaginatedPane(0,0);
        pagedpane.populateWithGuiItems(guiItemList);
        super.gui.addPane(pagedpane);
        long secondtime = firstTime - System.currentTimeMillis();
        Log.debug(String.valueOf(secondtime));
    }
}
