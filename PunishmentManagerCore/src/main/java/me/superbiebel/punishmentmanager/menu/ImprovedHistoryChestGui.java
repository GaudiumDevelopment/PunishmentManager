package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
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
import me.superbiebel.punishmentmanager.menu.abstraction.AbstractChestGui;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import me.superbiebel.punishmentmanager.utils.Log;
import me.superbiebel.punishmentmanager.utils.TimeDuration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ImprovedHistoryChestGui extends AbstractChestGui {
    private StaticPane buttonPane;
    private PaginatedPane paginatedPane;


    private ItemStack backToActionListGuiItemStack;
    private ItemMeta backToActionListGuiItemMeta;
    private GuiItem backToActionListGuiItem;

    private ItemStack previousPageItemStack;
    private ItemMeta previousPageItemMeta;
    private GuiItem previousPageItem;

    private ItemStack nextPageItemStack;
    private ItemMeta nextPageItemMeta;
    private GuiItem nextPageItem;

    private ItemStack fetchFromDbItemStack;
    private ItemMeta fetchFromDbItemMeta;
    private GuiItem fetchFromDbItem;


    private Player criminal;
    private String finaltitle;



    public ImprovedHistoryChestGui() {
        super();
    }

    @Override
    public void open(Player p) {
        super.cachedPlayer = p;
        super.gui.show(p);
    }

    @Override
    public void construct(boolean force, boolean allowlazy) {
        throw new IllegalArgumentException("Cannot construct gui without player");
    }

    @Override
    public void construct(boolean force, boolean allowlazy, @NotNull Player player) {
        /*
        - init gui object or get it from the cache
        if not in cache: continue
        - look up the data for the gui
        - construct items one by one with fetched data
        - format the paginated pane
        - format the buttons
        - show the gui
         */



        criminal = Metadata.provideForPlayer(player).get(DATAKEYS.CRIMINAL_KEY).get();
        UUID playeruuid = player.getUniqueId();
        super.gui = new ChestGui(6, "(fetching...)");

        Promise.start().thenRunAsync(() -> {
            finaltitle = criminal.getName() == criminal.getDisplayName() ? criminal.getName() : criminal.getDisplayName() + " aka " + criminal.getName();
            ChestGui cachedGui = DataHandlerProvider.getDataHandler().getCachedInventory("history;" + playeruuid.toString());


            if (!(cachedGui == null)) {
                super.gui = cachedGui;
            } else {
                constructHistoryPaneList();
                paginatedPane = new PaginatedPane(0, 0, 9, 5, Pane.Priority.HIGH);
                paginatedPane.populateWithGuiItems(constructHistoryPaneList());
            }


        }).thenRunSync(() -> {

            this.buttonPane = new StaticPane(0, 5, 9, 1);


            this.backToActionListGuiItemStack = new ItemStack(Material.BARRIER);
            this.backToActionListGuiItemMeta = this.backToActionListGuiItemStack.getItemMeta();
            this.backToActionListGuiItemMeta.setDisplayName(ColorUtils.colorize("&cBack To &lActions"));
            this.backToActionListGuiItemStack.setItemMeta(this.backToActionListGuiItemMeta);
            this.backToActionListGuiItem = new GuiItem(this.backToActionListGuiItemStack);

            this.backToActionListGuiItem.setAction(e -> {
                e.setCancelled(true);
                ImprovedActionListChestGui actionListChestGui = new ImprovedActionListChestGui();
                actionListChestGui.construct(false,false);
                actionListChestGui.open(cachedPlayer);
            });

            this.buttonPane.addItem(this.backToActionListGuiItem, 4, 0);

            this.previousPageItemStack = new ItemStack(Material.ARROW);

            this.previousPageItemMeta = this.previousPageItemStack.getItemMeta();
            this.previousPageItemMeta.setDisplayName(ColorUtils.colorize("&cPrevious Page"));
            List<String> previousPageItemLore = this.previousPageItemMeta.getLore();
            if (previousPageItemLore != null) {
                previousPageItemLore.add(ColorUtils.colorize("&cCurrently on page ") + paginatedPane.getPage());
            } else {
                previousPageItemLore = new ArrayList<>();
                previousPageItemLore.add(ColorUtils.colorize("&cCurrently on page ") + paginatedPane.getPage());
            }
            previousPageItemMeta.setLore(previousPageItemLore);

            this.previousPageItemStack.setItemMeta(this.previousPageItemMeta);
            this.previousPageItem = new GuiItem(this.previousPageItemStack);

            this.previousPageItem.setAction(e->{
                e.setCancelled(true);
                checkPreviousPageItem(true);
            });

            this.buttonPane.addItem(this.previousPageItem, 3, 0);


            this.nextPageItemStack = new ItemStack(Material.ARROW);

            this.nextPageItemMeta = this.nextPageItemStack.getItemMeta();
            this.nextPageItemMeta.setDisplayName(ColorUtils.colorize("&cNext Page"));
            List<String> nextPageItemLore = this.nextPageItemMeta.getLore();
            if (nextPageItemLore != null) {
                nextPageItemLore.add(0,ColorUtils.colorize("&cCurrently on page " + paginatedPane.getPage()));
            } else {
                nextPageItemLore = new ArrayList<>();
                nextPageItemLore.add(0,ColorUtils.colorize("&cCurrently on page " + paginatedPane.getPage()));
            }
            nextPageItemMeta.setLore(nextPageItemLore);

            this.nextPageItemStack.setItemMeta(this.nextPageItemMeta);
            this.nextPageItem = new GuiItem(this.nextPageItemStack);

            this.nextPageItem.setAction(e->{
                e.setCancelled(true);
                checkNextPageItem(true);
                List<String> lore = this.nextPageItemMeta.getLore();
                lore.remove(0);
                lore.add(0,ColorUtils.colorize("&cCurrently on page " + paginatedPane.getPage()));
            });

            this.buttonPane.addItem(this.nextPageItem,5, 0);

            this.fetchFromDbItemStack = new ItemStack(Material.PINK_DYE);
            this.fetchFromDbItemMeta = fetchFromDbItemStack.getItemMeta();
            this.fetchFromDbItemMeta.setDisplayName(ColorUtils.colorize("&bFetch from Database"));
            this.fetchFromDbItemStack.setItemMeta(this.fetchFromDbItemMeta);
            this.fetchFromDbItem = new GuiItem(this.fetchFromDbItemStack);
            this.fetchFromDbItem.setAction(e->{
                e.setCancelled(true);
                this.paginatedPane.clear();
            });


            super.gui.addPane(this.buttonPane);
            super.gui.addPane(this.paginatedPane);
            super.gui.setTitle(this.finaltitle);

            checkPreviousPageItem(false);
            checkNextPageItem(false);

            super.gui.update();
        });
    }

        private void checkNextPageItem(boolean modifyPageNumber) {
            //check if you can go further a page
            if (modifyPageNumber && this.paginatedPane.getPage() < (this.paginatedPane.getPages()-1)) {
                this.paginatedPane.setPage(this.paginatedPane.getPage() + 1);
                checkPreviousPageItem(false);
            }
            if (this.paginatedPane.getPage() >= (this.paginatedPane.getPages() - 1)) {
                this.nextPageItem.setVisible(false);
            } else {
                this.nextPageItem.setVisible(true);
            }
            super.gui.update();
        }

    private void checkPreviousPageItem(boolean modifyPageNumber) {

        //check if you can go a page back
        if (modifyPageNumber && this.paginatedPane.getPage() > 0) {
            Log.debug("decreasing page");
            this.paginatedPane.setPage(this.paginatedPane.getPage() - 1);
            Log.debug("checking next page item");
            checkNextPageItem(false);
        }
        if (this.paginatedPane.getPage() == 0) {
            Log.debug("setting visibility to false (previous)");
            this.previousPageItem.setVisible(false);
        } else {
            Log.debug("setting visibility to true (previous)");
            this.previousPageItem.setVisible(true);
        }
            super.gui.update();
    }
    
    
    

    private List<GuiItem> constructHistoryPaneList() {

        // List<HistoryRecord> historyRecordList = DataHandlerProvider.getDataHandler().getHistory(playeruuid); //TODO: uncomment this when the data modules are implemented (this is just for testing)
        List<GuiItem> historyItemList = new ArrayList<>();
        //purely for testing purposes ---------------------------------
        List<HistoryRecord> historyRecordList = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            historyRecordList.add(new HistoryRecord(UUID.randomUUID(), Material.ACACIA_BOAT, 1, "&cTESTING OFFENSE1", "THIS IS A REASON", -1, 20, 20000, 3000));
        }
        for (int i = 0; i < 50; i++) {
            historyRecordList.add(new HistoryRecord(UUID.randomUUID(), Material.IRON_AXE, 1, "&cTESTING OFFENSE2", "THIS IS A REASON", -1, 200, 2000, 300));
        }
        //------------------------------------------------------------------

        Collections.sort(historyRecordList);
        if (historyRecordList == null) {
            return historyItemList;
        }
        for (HistoryRecord historyRecord : historyRecordList) {

            historyItemList.add(buildHistoryItem(historyRecord));

        }
        return historyItemList;
    }

    private GuiItem buildHistoryItem(HistoryRecord historyRecord) {

        ItemStack historyItemStack = new ItemStack(historyRecord.getMaterial());

        ItemMeta historyItemMeta = historyItemStack.getItemMeta();
        historyItemMeta.setDisplayName(ColorUtils.colorize(historyRecord.getOffenseName()));
        List<String> historyLore = new ArrayList<>();
        historyLore.add(formatHours(historyRecord));
        historyItemMeta.setLore(historyLore);

        historyItemStack.setItemMeta(historyItemMeta);

        GuiItem historyItem = new GuiItem(historyItemStack);
        historyItem.setAction(e -> {
            e.setCancelled(true);
        });


        return historyItem;
    }

    private String formatHours(HistoryRecord historyRecord) {
        long durationToCurrent = System.currentTimeMillis() - historyRecord.getCreationdate();

        TimeDuration duration = new TimeDuration(durationToCurrent);
        StringBuilder stringbuilder = new StringBuilder();
        boolean yearsAreUsed = false;
        boolean monthsAreUsed = false;
        boolean daysAreUsed = false;
        TimeDuration.TIME timeAmount = null;


        if (!(duration.getWholeYears() == 0)) {

            stringbuilder.append(duration.getWholeYears());
            stringbuilder.append(" years");
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
        return stringbuilder.toString();
    }
}
