package me.superbiebel.punishmentmanager.data.dataObjects;

import lombok.Getter;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

public class HistoryRecord {
    @Getter
    int uuid;
    @Getter
    Material material;
    @Getter
    int offenseId;
    @Getter
    int punishmentId;
    
    public HistoryRecord() {
    }
    public HistoryRecord(int uuid, String material, int offenseId, int punishmentId, String formattedReason, @Nullable int jailid) {
    
    }
    
}
