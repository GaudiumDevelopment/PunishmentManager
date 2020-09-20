package me.superbiebel.punishmentmanager.utils;

import me.lucko.helper.metadata.MetadataKey;
import org.bukkit.entity.Player;

public class DataUtility {
    private static final MetadataKey<Player> CRIMINAL_KEY = MetadataKey.create("criminal",Player.class);

    public static MetadataKey<Player> getCriminalKey() {
        return CRIMINAL_KEY;
    }
}
