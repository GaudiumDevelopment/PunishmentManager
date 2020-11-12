package me.superbiebel.punishmentmanager.data;

import me.lucko.helper.metadata.MetadataKey;
import org.bukkit.entity.Player;

import java.util.List;

public class DataUtility {

    public static final MetadataKey<Player> CRIMINAL_KEY = MetadataKey.create("criminal",Player.class);
    public static final MetadataKey<List> ALLOWED_IDS_KEY = MetadataKey.create("allowed_ids",List.class);
    public static void init() {

    }
}
