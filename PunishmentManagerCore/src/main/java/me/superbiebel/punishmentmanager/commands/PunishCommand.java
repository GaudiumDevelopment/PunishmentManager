package me.superbiebel.punishmentmanager.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.context.CommandContext;
import me.lucko.helper.metadata.Metadata;
import me.superbiebel.punishmentmanager.data.DATAKEYS;
import me.superbiebel.punishmentmanager.menu.abstraction.AbstractChestGui;
import me.superbiebel.punishmentmanager.menu.ImprovedActionListChestGui;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import me.superbiebel.punishmentmanager.utils.Log;
import me.superbiebel.punishmentmanager.utils.PERMISSIONS;
import me.superbiebel.punishmentmanager.utils.PermissionUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PunishCommand {
    
    @CommandMethod("punish <player>")
    @CommandPermission("punishmentmanager.command.punish")
    public void onCommand(CommandSender sender, @Argument(value = "player", suggestions = "punishcommand") String criminalName) {
        if (!PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.punish")) return;

        if (sender instanceof ConsoleCommandSender){
            sender.sendMessage(ColorUtils.colorize(Log.getWarningPrefix() + " Only ingame players can do this command!"));
            return;
        }

        if (criminalName == "" | criminalName == null) {
            sender.sendMessage(ColorUtils.colorize(Log.getWarningPrefix() + " No player specified"));
            return;
        }

        Player criminal = Bukkit.getPlayer(criminalName);
        if (criminal == null) {
            criminal = Bukkit.getPlayer(UUID.fromString(criminalName));
            if (criminal == null) {
                sender.sendMessage(ColorUtils.colorize(Log.getWarningPrefix() + " Player not found."));
                return;
            }
        }

        Metadata.provideForPlayer((Player) sender).put(DATAKEYS.CRIMINAL_KEY, criminal);
        AbstractChestGui actionListGui = new ImprovedActionListChestGui();
        actionListGui.construct(false,false);
        actionListGui.open((Player) sender);
        
        
        
        /*if (sender instanceof Player) {
            if (PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.punish")) {
                Player p = (Player) sender;
                Player criminal = Bukkit.getPlayerExact(player);
                if (!(criminal == null)) {
                    MetadataMap metadata = Metadata.provideForPlayer(p);
                    metadata.put(DATAKEYS.CRIMINAL_KEY, criminal);
                    new ImprovedActionListChestGui().construct(false,false).open(p);
                } else {
                    sender.sendMessage(ColorUtils.colorize(Log.getFatalErrorPrefix() + " Player not found!"));
                }
            } else {
                Log.warning("Only an ingame player can run the punish command", false, true, false);
            }
        }*/
    }

    @Suggestions("punishcommand")
    public List<String> subcommandArgSuggestion(CommandContext<CommandSender> context, String input) {
        List<String> suggestions = new ArrayList<>();
        if (!context.getSender().hasPermission(PERMISSIONS.PUNISHCOMMAND)) return suggestions;
        input = input.toLowerCase();
        for (Player p : Bukkit.getOnlinePlayers()){
            if (p.getName().toLowerCase().contains(input)) {
                suggestions.add(p.getName());
            }
        }
        return suggestions;
    }
}
