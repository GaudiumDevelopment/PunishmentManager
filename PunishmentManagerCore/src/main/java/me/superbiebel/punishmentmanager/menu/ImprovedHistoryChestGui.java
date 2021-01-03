package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.lucko.helper.metadata.Metadata;
import me.superbiebel.punishmentmanager.data.DATAKEYS;
import me.superbiebel.punishmentmanager.data.dataObjects.HistoryRecord;
import me.superbiebel.punishmentmanager.data.providers.DataHandlerProvider;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImprovedHistoryChestGui extends AbstractChestGui{
    private ChestGui gui;
    private StaticPane pane;
    private ItemStack testItemStack;
    private GuiItem testItem;
    private ItemMeta testItemMeta;
    private Player player;
    
    public ImprovedHistoryChestGui() {
        super();
    }
    @Override
    public void open(Player p){
        Log.debug("open got called");
        gui.setTitle("History of " + Metadata.provideForPlayer(p).get(DATAKEYS.CRIMINAL_KEY));
        gui.show(p);
    }
    
    @Override
    public void construct(boolean force, boolean allowlazy) {
        throw new IllegalArgumentException("Cannot construct gui without player");
    }
    public void construct(boolean force, boolean allowlazy, Player player){
        UUID playeruuid = player.getUniqueId();
        if (DataHandlerProvider.getDataHandler().getCachedInventory("history;" + playeruuid.toString()) == null) {
            List<HistoryRecord> historyRecordList = new ArrayList<>();
            historyRecordList = DataHandlerProvider.getDataHandler().getHistory(playeruuid);
            for (HistoryRecord historyRecord : historyRecordList) {
            
            }
        }
    }
}
