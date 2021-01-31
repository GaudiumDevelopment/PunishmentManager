package me.superbiebel.punishmentmanager.data.dataObjects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
@Setter
@Getter
public class HistoryRecord implements Comparable<HistoryRecord>{

    private long creationdate;
    private UUID uuid;
    private Material material;
    private int offenseId;
    private String formattedReason;
    private int jailid;
    private String offenseName;
    private long muteduration;
    private long banduration;


    public HistoryRecord(UUID historyID, Material material, int offenseId, String offenseName, String formattedReason, int jailid, long creationdate, long muteduration, long banduration) {
        this.uuid = historyID;
        this.material = material;
        this.offenseId = offenseId;
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
