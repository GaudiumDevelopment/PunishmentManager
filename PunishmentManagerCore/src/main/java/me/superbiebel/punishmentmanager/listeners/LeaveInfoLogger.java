package me.superbiebel.punishmentmanager.listeners;

import me.lucko.helper.Events;
import me.lucko.helper.event.SingleSubscription;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveInfoLogger implements AbstractListener {

    SingleSubscription<PlayerQuitEvent> quitEvent;
    SingleSubscription<PlayerKickEvent> kickevent;

    @Override
    public void init() {
        quitEvent = Events.subscribe(PlayerQuitEvent.class).handler(this::handleQuit);
        kickevent = Events.subscribe(PlayerKickEvent.class).handler(this::handleKick);
    }

    @Override
    public void shutdown() {
        quitEvent.close();
        kickevent.close();
    }

    public void handleQuit(PlayerQuitEvent e) {
        throw new UnsupportedOperationException("Operation not implemented yet");
    }
    public void handleKick(PlayerKickEvent e) {
        throw new UnsupportedOperationException("Operation not implemented yet");
    }


}
