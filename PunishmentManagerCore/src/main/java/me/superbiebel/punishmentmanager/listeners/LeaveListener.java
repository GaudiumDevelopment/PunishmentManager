package me.superbiebel.punishmentmanager.listeners;

import me.lucko.helper.Schedulers;
import me.superbiebel.punishmentmanager.data.datahandler.DataHandlerManager;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class LeaveListener {

    public void handleQuit(PlayerQuitEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        String leaveMessage = e.getQuitMessage();
        Schedulers.async().run(()->{
        try {
            DataHandlerManager.getDataHandler().insertLeave(uuid,leaveMessage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        });
    }
    public void handleKick(PlayerKickEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        String kickMessage = e.getReason();
        String leaveMessage = e.getLeaveMessage();
        Schedulers.async().run(()->{
            try {
                DataHandlerManager.getDataHandler().insertKick(uuid,kickMessage,leaveMessage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
