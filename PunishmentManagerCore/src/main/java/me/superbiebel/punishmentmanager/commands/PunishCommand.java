package me.superbiebel.punishmentmanager.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import lombok.Getter;
import me.superbiebel.punishmentmanager.menu.ImprovedActionListChestGui;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class PunishCommand {
    
    @CommandMethod("punish <player>")
    @CommandPermission("punishmentmanager.command.punish")
    public void onCommand(CommandSender sender, @Argument(value = "player", suggestions = "punishcommand") String player) {
        if (player == "" | player == null) {
            sender.sendMessage(ColorUtils.colorize(Log.getWarningPrefix() + " No player specified"));
            return;
        }
    
        ImprovedActionListChestGui actionListGui = new ImprovedActionListChestGui();
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
    @Getter
    private static BiFunction<@NonNull CommandContext<CommandSender>, @NonNull String, @NonNull List<String>> suggestionsProvider = (context, input) -> {
        ArrayList suggestions = new ArrayList<String>();
        for (Player p : Bukkit.getOnlinePlayers()){
            if (p.getName().contains(input)) {
                suggestions.add(p.getName());
            }
        }
        return suggestions;
    };
}
