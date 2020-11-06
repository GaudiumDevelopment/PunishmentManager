package me.superbiebel.punishmentmanager.data;

import me.lucko.helper.bucket.Bucket;
import me.lucko.helper.bucket.factory.BucketFactory;
import me.lucko.helper.bucket.partitioning.PartitioningStrategies;
import me.lucko.helper.metadata.MetadataKey;
import org.bukkit.entity.Player;

import java.util.List;

public class DataUtility {

    public static final MetadataKey<Player> CRIMINAL_KEY = MetadataKey.create("criminal",Player.class);
    public static final MetadataKey<List> ALLOWED_IDS_KEY = MetadataKey.create("allowed_ids",List.class);
    public static final Bucket<Player> id_bucket = BucketFactory.newHashSetBucket(20, PartitioningStrategies.nextInCycle());
    public static void init() {

    }
}
