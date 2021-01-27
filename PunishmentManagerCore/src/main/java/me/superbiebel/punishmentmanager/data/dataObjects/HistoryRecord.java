package me.superbiebel.punishmentmanager.data.dataObjects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HistoryRecord implements Comparable<HistoryRecord>{
    @Setter
    @Getter
    private long creationdate;
    @Setter
    @Getter
    private UUID uuid;
    @Setter
    @Getter
    private Material material;
    @Setter
    @Getter
    private int offenseId;
    @Setter
    @Getter
    private int punishmentId;
    @Setter
    @Getter
    private String formattedReason;
    @Setter
    @Getter
    private int jailid;
    @Setter
    @Getter
    private String offenseName;

    public HistoryRecord(UUID historyID, Material material, int offenseId, int punishmentId, String offenseName, String formattedReason, int jailid, long creationdate, long muteduration, long banduration) {
        this.uuid = historyID;
        this.material = material;
        this.offenseId = offenseId;
        this.punishmentId = punishmentId;
        this.formattedReason = formattedReason;
        this.jailid = jailid;
        this.offenseName = offenseName;
        this.creationdate = creationdate;
    }

    public HistoryRecord() {
    }

    @Override
    public int compareTo(@NotNull HistoryRecord o) {
        return (int) (this.creationdate - o.getCreationdate());
    }
}
