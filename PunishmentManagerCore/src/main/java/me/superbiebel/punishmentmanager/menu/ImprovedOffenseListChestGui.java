package me.superbiebel.punishmentmanager.menu;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import me.lucko.helper.promise.Promise;
import me.lucko.helper.promise.ThreadContext;
import org.bukkit.entity.Player;

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
        ThreadContext threadContext = allowlazy ? ThreadContext.ASYNC : ThreadContext.SYNC;
        Promise<Void> constructpanePromise = Promise.empty().thenRunSync(()->{
            super.gui = new ChestGui(6,"Choose an offense");
        }).thenRun(threadContext,()->{
        
        });
    }
}
