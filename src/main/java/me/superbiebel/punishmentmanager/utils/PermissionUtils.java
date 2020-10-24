package me.superbiebel.punishmentmanager.utils;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.entity.Player;

public class PermissionUtils {
    public static boolean check(Player p, String permission) {
        if (!p.hasPermission(permission)) {
            ColorUtils.colorize(PunishmentManager.giveConfig().getString("messages.noPermissionMessage"));
            return false;
        }
        return true;
    }
}
