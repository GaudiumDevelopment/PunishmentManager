package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ImprovedActionListChestGui extends AbstractChestGui {
    
    private StaticPane staticPane;
    
    private ItemStack offenseItemStack;
    private GuiItem offenseItem;
    private ItemMeta offenseItemMeta;
    
    private ItemStack historyItemStack;
    private GuiItem historyItem;
    private ItemMeta historyItemMeta;
    
    private ItemStack altCheckItemStack;
    private GuiItem altCheckItem;
    private SkullMeta altCheckItemMeta;
    
    @Override
    public void construct(boolean force, boolean allowlazy){
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
        
        altCheckItemStack = new ItemStack(Material.PLAYER_HEAD);
        altCheckItemMeta = (SkullMeta) altCheckItemStack.getItemMeta();
        
        
        
        
        staticPane.addItem(offenseItem,2,1);
        staticPane.addItem(historyItem,4,1);
        
        
        super.hasBeenConstructed = true;
    }
    
    @Override
    public void construct(boolean force, boolean allowlazy, Player player) {
        construct(force, allowlazy);
        cachedPlayer = player;
        personalisedStuff();
    }
    
    @Override
    public void open(final Player p){
        cachedPlayer = p;
        personalisedStuff();
        super.gui.addPane(staticPane);
        super.gui.show(p);
    }
    
    @Override
    public void open() {
        super.gui.show(cachedPlayer);
    }
    
    private void personalisedStuff() {
        altCheckItemMeta.setOwningPlayer(cachedPlayer);
        altCheckItemStack.setItemMeta(altCheckItemMeta);
        altCheckItem = new GuiItem(altCheckItemStack);
        staticPane.addItem(altCheckItem,6,1);
        
        offenseItem.setAction(e -> {
            e.setCancelled(true);
            AbstractChestGui gui = new ImprovedOffenseListChestGui();
            gui.construct(false,true);
            gui.open(cachedPlayer);
        });
        historyItem.setAction((e->{
            e.setCancelled(true);
            AbstractChestGui historyGui = new ImprovedHistoryChestGui();
            historyGui.construct(false,true, cachedPlayer);
            historyGui.open(cachedPlayer);
        }));
        
        altCheckItem.setAction(e->{
            e.setCancelled(true);
            AbstractChestGui altcheckGui = new AltCheckGui();
            altcheckGui.construct(false,true);
            altcheckGui.open(cachedPlayer);
        });
    }
    
    @Override
    public void setCachedPlayer(Player p) {
        cachedPlayer = p;
        personalisedStuff();
    }
}
