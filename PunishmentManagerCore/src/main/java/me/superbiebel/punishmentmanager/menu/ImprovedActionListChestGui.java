package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ImprovedActionListChestGui extends AbstractChestGui {
    private StaticPane staticPane;
    private ItemStack offenseItemStack;
    private GuiItem offenseItem;
    private ItemMeta offenseItemMeta;
    private ItemStack historyItemStack;
    private GuiItem historyItem;
    private ItemMeta historyItemMeta;
    
    @Override
    public void construct(boolean force, boolean allowlazy){
        /*if (hasBeenConstructed || !force){
            Log.debug("already constructed");
            return this;
        }*/
        super.gui = new ChestGui(3, "Choose action");
        staticPane = new StaticPane(0,0,9,3);
        
        offenseItemStack = new ItemStack(Material.IRON_AXE);
        offenseItemMeta = offenseItemStack.getItemMeta();
        offenseItemMeta.setDisplayName(ColorUtils.colorize("&4&lNew Offense"));
        offenseItemStack.setItemMeta(offenseItemMeta);
        offenseItem = new GuiItem(offenseItemStack);
        
        historyItemStack = new ItemStack(Material.BOOK);
        historyItemMeta = historyItemStack.getItemMeta();
        historyItemMeta.setDisplayName(ColorUtils.colorize("&4&lHistory"));
        historyItemStack.setItemMeta(historyItemMeta);
        historyItem = new GuiItem(historyItemStack);
        
        
        staticPane.addItem(offenseItem,2,1);
        staticPane.addItem(historyItem,4,1);
        
        super.gui.addPane(staticPane);
        super.hasBeenConstructed = true;
    }
    @Override
    public void open(final Player p){
        offenseItem.setAction(e -> {
            e.setCancelled(true);
            Log.info("button works",true,true,false);
            //new ImprovedOffenseListChestGui().construct(false,false).open(p);
        });
        offenseItem.setAction((e->{
            e.setCancelled(true);
            Log.info("historybutton works",true,true,false);
            AbstractChestGui historyGui = new ImprovedHistoryChestGui();
            historyGui.construct(false,true);
            historyGui.open(p);
        }));
        
        super.gui.show(p);
    }
}
