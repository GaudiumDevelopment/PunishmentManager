package me.superbiebel.punishmentmanager.utils;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class PermissionUtils {
    public static boolean check(@NotNull CommandSender sender, @NotNull String permission) {
        if (sender instanceof ConsoleCommandSender) {
            return true;
        } else {
        return sender.hasPermission(permission);}
    }

    public static boolean checkAndMessage(@NotNull CommandSender sender, @NotNull String permission) {
        if (sender instanceof ConsoleCommandSender) {
            return true;
        } else if (!sender.hasPermission(permission)) {
            sender.sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
            return false;
        } else {
            //safety so IntelliJ doesn't complain
            return false;
        }

    }
}
