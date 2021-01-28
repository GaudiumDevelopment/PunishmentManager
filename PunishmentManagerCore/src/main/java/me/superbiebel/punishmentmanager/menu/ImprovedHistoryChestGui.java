package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import me.lucko.helper.metadata.Metadata;
import me.lucko.helper.promise.Promise;
import me.superbiebel.punishmentmanager.data.DATAKEYS;
import me.superbiebel.punishmentmanager.data.dataObjects.HistoryRecord;
import me.superbiebel.punishmentmanager.data.providers.DataHandlerProvider;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import me.superbiebel.punishmentmanager.utils.TimeDuration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ImprovedHistoryChestGui extends AbstractChestGui{
    private StaticPane buttonPane;
    private PaginatedPane paginatedPane;


    private ItemStack backToActionListGuiItemStack;
    private ItemMeta backToActionListGuiItemMeta;
    private GuiItem backToActionListGuiItem;
    private Player criminal;
    private String finaltitle;
    private List<GuiItem> historyItemList;

    
    public ImprovedHistoryChestGui() {
        super();
    }
    @Override
    public void open(Player p){
        super.cachedPlayer = p;
        super.gui.show(p);
    }
    
    @Override
    public void construct(boolean force, boolean allowlazy) {
        throw new IllegalArgumentException("Cannot construct gui without player");
    }
    @Override
    public void construct(boolean force, boolean allowlazy, @NotNull Player player){
        historyItemList = new LinkedList<>();
        criminal = Metadata.provideForPlayer(player).get(DATAKEYS.CRIMINAL_KEY).get();
        UUID playeruuid = player.getUniqueId();
        super.gui = new ChestGui(6, "(fetching...)");

        Promise.start().thenRunAsync(()->{
            finaltitle = criminal.getName() == criminal.getDisplayName() ? criminal.getName() : criminal.getDisplayName() + " aka " + criminal.getName();
            ChestGui cachedGui = DataHandlerProvider.getDataHandler().getCachedInventory("history;" + playeruuid.toString());


            if (!( cachedGui == null)) {
                super.gui = cachedGui;
            } else {

               // List<HistoryRecord> historyRecordList = DataHandlerProvider.getDataHandler().getHistory(playeruuid); //TODO: uncomment this when the data modules are implemented (this is just for testing)

                //purely for testing purposes ---------------------------------
                List<HistoryRecord> historyRecordList = new LinkedList<>();
                for (int i = 0; i < 50;i++){
                    historyRecordList.add(new HistoryRecord(UUID.randomUUID(), Material.ACACIA_BOAT,1,1,"&cTESTING OFFENSE1", "THIS IS A REASON",-1, 20, 20000,3000)  );

                }
                //------------------------------------------------------------------

                Collections.sort(historyRecordList);
                if (historyRecordList == null) {
                    return;
                }
                for (HistoryRecord historyRecord : historyRecordList) {
                    ItemStack historyItemStack = new ItemStack(historyRecord.getMaterial());


                    ItemMeta historyItemMeta = historyItemStack.getItemMeta();
                    historyItemMeta.setDisplayName(ColorUtils.colorize(historyRecord.getOffenseName()));
                    List<String> historyLore = new ArrayList<>();
                    //long durationToCurrent = System.currentTimeMillis() - historyRecord.getCreationdate(); //TODO: uncomment this when the data modules are implemented (this is just for testing)
                    long durationToCurrent = 26640000L;

                    TimeDuration duration = new TimeDuration(durationToCurrent);
                    StringBuilder stringbuilder = new StringBuilder();
                    boolean yearsAreUsed = false;
                    boolean monthsAreUsed = false;
                    boolean daysAreUsed = false;
                    TimeDuration.TIME timeAmount = null;


                    if (!(duration.getWholeYears() == 0)) {

                        stringbuilder.append(duration.getWholeYears());
                        stringbuilder.append("years");
                        yearsAreUsed = true;
                        timeAmount = TimeDuration.TIME.YEAR;
                    }
                    if (!(duration.getWholeMonths() == 0)) {
                        if (!(timeAmount == null)) {
                            stringbuilder.append(" ");
                        }
                        stringbuilder.append(duration.getWholeMonths());
                        stringbuilder.append(" months");
                        monthsAreUsed = true;
                        timeAmount = TimeDuration.TIME.MONTH;
                    }
                    if (!(duration.getWholeDays() == 0)) {
                        if (!(timeAmount == null)) {
                            stringbuilder.append(" ");
                        }
                        stringbuilder.append(duration.getWholeDays());
                        stringbuilder.append(" days");
                        daysAreUsed = true;
                        timeAmount = TimeDuration.TIME.DAY;
                    }
                    if (!(duration.getWholeHours() == 0) && !(yearsAreUsed) && !(monthsAreUsed)) {
                        if (!(timeAmount == null)) {
                            stringbuilder.append(" ");
                        }
                        stringbuilder.append(duration.getWholeHours());
                        stringbuilder.append(" hours");
                        timeAmount = TimeDuration.TIME.HOUR;
                    }
                    if (!(duration.getWholeMinutes() == 0) && !(yearsAreUsed) && !(monthsAreUsed) && !(daysAreUsed)) {
                        if (!(timeAmount == null)) {
                            stringbuilder.append(" ");
                        }
                        stringbuilder.append(duration.getWholeMinutes());
                        stringbuilder.append(" minutes");
                        timeAmount = TimeDuration.TIME.MINUTE;

                    }
                    if (!(duration.getWholeseconds() == 0) && !(yearsAreUsed) && !(monthsAreUsed) && !(daysAreUsed)) {
                        if (!(timeAmount == null)) {
                            stringbuilder.append(" ");
                        }
                        stringbuilder.append(duration.getWholeseconds());
                        stringbuilder.append(" seconds");
                    }
                    stringbuilder.append(" ago");
                    String timestamp = stringbuilder.toString();

                    historyLore.add(timestamp);
                    historyItemMeta.setLore(historyLore);
                    historyItemStack.setItemMeta(historyItemMeta);

                    GuiItem historyItem = new GuiItem(historyItemStack);
                    historyItem.setAction(e->{
                        e.setCancelled(true);
                    });
                    historyItemList.add(historyItem);
                }

            }



        }).thenRunSync(()->{


            backToActionListGuiItemStack = new ItemStack(Material.BARRIER);
            backToActionListGuiItemMeta = backToActionListGuiItemStack.getItemMeta();
            backToActionListGuiItemMeta.setDisplayName(ColorUtils.colorize("&c&lBack"));
            backToActionListGuiItemStack.setItemMeta(backToActionListGuiItemMeta);
            backToActionListGuiItem = new GuiItem(backToActionListGuiItemStack);


            backToActionListGuiItem.setAction(e->{
                e.setCancelled(true);
                ImprovedActionListChestGui actionListChestGui = new ImprovedActionListChestGui();
                actionListChestGui.construct(false,true);
                actionListChestGui.open((Player) e.getWhoClicked());
            });
            buttonPane = new StaticPane(4,5,9, 1);
            buttonPane.addItem(backToActionListGuiItem,0,0);
            super.gui.addPane(buttonPane);

            super.gui.setTitle(finaltitle);
            super.gui.update();
        });

    }
}
