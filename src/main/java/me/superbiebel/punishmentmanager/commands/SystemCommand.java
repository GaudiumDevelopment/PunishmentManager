package me.superbiebel.punishmentmanager.commands;

import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.punishmentcore.OffenseExecutor;
import me.superbiebel.punishmentmanager.utils.PermissionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SystemCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if (args.length == 0 | args.length > 1) {

                    sender.sendMessage("1 argument is required");

                } else if (args[0].equalsIgnoreCase("help") && PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.system.help")) {

                    sender.sendMessage("Check out the wiki if you need help");

                } else if (args[0].equalsIgnoreCase("reloadconfig") && PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.system.reloadconfig")) {

                    sender.sendMessage("Reloading config...");
                    PunishmentManager.getPlugin().reloadConfig();
                    sender.sendMessage("Config reloaded");

                } else if (args[0].equalsIgnoreCase("sync") && PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.system.sync")) {

                    sender.sendMessage("Syncing...");
                    sender.sendMessage("Sync complete");

                } else if (args[0].equalsIgnoreCase("test") && sender instanceof Player) {

                    new OffenseExecutor((Player) sender, -1).execute();

                } else {

                    sender.sendMessage("Argument not recognised");

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
        if (commandSender.hasPermission("punishmentmanager.command.system.test")){
            tabComplete.add("test");
        }

        return tabComplete;
    }
}
