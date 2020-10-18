package me.superbiebel.punishmentmanager.commands;

import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.punishmentcore.OffenseExecutor;
import me.superbiebel.punishmentmanager.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SystemCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("punishmentmanager.command.system")) {

            if (args.length == 0 | args.length > 1) {
                sender.sendMessage("1 argument is required");

            } else if (args[0].equalsIgnoreCase("help")) {

                if (sender.hasPermission("punishmentmanager.command.system.help")){
                sender.sendMessage("Check out the wiki if you need help");
                } else {
                    sender.sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
                }


            } else if (args[0].equalsIgnoreCase("reloadconfig")) {

                if (sender.hasPermission("punishmentmanager.command.system.reloadconfig")){
                sender.sendMessage("Reloading config...");
                PunishmentManager.getPlugin().reloadConfig();
                sender.sendMessage("Config reloaded");
                } else {
                    sender.sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
                }

            } else if (args[0].equalsIgnoreCase("sync")) {
                if (sender.hasPermission("punishmentmanager.command.system.sync")) {
                    sender.sendMessage("Syncing....");
                    sender.sendMessage("Sync complete");
                } else {
                    sender.sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
                }
            } else if(args[0].equalsIgnoreCase("test")) {
                new OffenseExecutor((Player) sender,-1).execute();
            } else {
                sender.sendMessage("Argument not recognised");
            }
            return true;
        } else {
            sender.sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
        }
    return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabComplete = new ArrayList<>();
        if (commandSender.hasPermission("punishmentmanager.command.system.help")){
            tabComplete.add("help");
        }
        if (commandSender.hasPermission("punishmentmanager.command.system.reloadconfig")){
            tabComplete.add("reloadconfig");
        }
        if (commandSender.hasPermission("punishmentmanager.command.system.sync")){
            tabComplete.add("sync");
        }
        if (commandSender.hasPermission("punishmentmanager.command.system.tab")){
            tabComplete.add("tab");
        }

        return tabComplete;
    }
}
