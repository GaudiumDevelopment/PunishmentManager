package me.superbiebel.punishmentmanager.listeners;

import me.lucko.helper.Events;
import me.lucko.helper.event.SingleSubscription;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class DataServiceStartupSafety implements AbstractListener{

    SingleSubscription<AsyncPlayerPreLoginEvent> event;

    @Override
    public void init() {
        event = Events.subscribe(AsyncPlayerPreLoginEvent.class).handler(this::onJoin);
    }

    @Override
    public void shutdown() {
        event.close();
    }

    public void onJoin(AsyncPlayerPreLoginEvent e) {
        if (PunishmentManager.getServiceManager() == null || PunishmentManager.getServiceManager().getServiceStartupComplete()) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Log.getFatalErrorPrefix() + "Still initializing database! Please wait 10-30 seconds and try to log back in!");
        }
    }


}
