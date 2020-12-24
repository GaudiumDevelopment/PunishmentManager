package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.lucko.helper.Schedulers;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ImprovedHistoryChestGui extends AbstractChestGui{
    private ChestGui gui;
    private StaticPane pane;
    private ItemStack testItemStack;
    private GuiItem testItem;
    private ItemMeta testItemMeta;
    
    public ImprovedHistoryChestGui() {
        super();
    }
    
    public void open(Player p){
        Log.debug("open got called");
        Schedulers.sync().runLater(()->{
            gui.show(p);
        },3);
    }
    
    
    public void construct(boolean force, boolean allowlazy) {
        gui = new ChestGui(6,"History of");
        testItemStack = new ItemStack(Material.ACACIA_BOAT);
        testItem = new GuiItem(testItemStack);
        pane = new StaticPane(0,0,9,6);
        pane.addItem(testItem,0,0);
        gui.addPane(pane);
    }
}
