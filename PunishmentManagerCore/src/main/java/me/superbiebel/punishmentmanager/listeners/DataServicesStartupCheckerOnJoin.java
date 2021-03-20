package me.superbiebel.punishmentmanager.listeners;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class DataServicesStartupCheckerOnJoin implements AbstractListener{

    @Override
    public void init() {

    }

    @Override
    public void shutdown() {

    }

    public void OnJoin(AsyncPlayerPreLoginEvent e) {
        //e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Log.getFatalErrorPrefix() + "Still initializing database!, please wait 10 seconds and log back in!");
    }


}
