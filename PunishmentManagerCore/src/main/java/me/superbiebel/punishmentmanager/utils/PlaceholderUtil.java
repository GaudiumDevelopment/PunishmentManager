package me.superbiebel.punishmentmanager.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;

public class PlaceholderUtil {
    private PlaceholderUtil() {
    }

    public static String parse(String input, OfflinePlayer player) {
        String parsedString = input;
        PlaceholderAPI.setPlaceholders(player, input);
        return parsedString;
    }
}
