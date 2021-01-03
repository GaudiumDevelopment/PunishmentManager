package me.superbiebel.punishmentmanager.data.dataObjects;

import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class HistoryRecord {
    UUID uuid;
    Material material;
    int offenseId;
    int punishmentId;
    
    public HistoryRecord() {
    }
    public HistoryRecord(UUID uuid, String material, int offenseId, int punishmentId, String formattedReason, @Nullable int jailid) {
    
    }
    
}
