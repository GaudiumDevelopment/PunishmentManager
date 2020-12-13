package me.superbiebel.punishmentmanager.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import me.lucko.helper.metadata.Metadata;
import me.lucko.helper.metadata.MetadataMap;
import me.superbiebel.punishmentmanager.data.DATAKEYS;
import me.superbiebel.punishmentmanager.menu.ActionsListGUI;
import me.superbiebel.punishmentmanager.utils.PermissionUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class PunishCommand{
    
    @CommandMethod("punish <player>")
    public boolean onCommand(CommandSender sender, @Argument("player") String player) {
        if(sender instanceof Player) {
            if (PermissionUtils.checkAndMessage(sender,"punishmentmanager.command.punish"))
                sender.sendMessage("No player provided!");
                Player p = (Player) sender;
                MetadataMap metadata = Metadata.provideForPlayer(p);
                metadata.put(DATAKEYS.CRIMINAL_KEY, Bukkit.getPlayerExact(player));
                new ActionsListGUI(p).open();
            
        }
        return true;
    }
}
