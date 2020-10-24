package me.superbiebel.punishmentmanager.utils;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PermissionUtils {
    public static boolean check(@NotNull Player p, @NotNull String permission) {
        return p.hasPermission(permission);
    }

    public static boolean checkAndMessage(@NotNull Player p, @NotNull String permission) {
        if (!p.hasPermission(permission)) {
            p.sendMessage(ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage")));
            return false;
        } else {
            return true;
        }
    }
}
