package me.superbiebel.punishmentmanager.data.dataObjects;

import lombok.Getter;

public class OffenseKey {
    @Getter
    private final int offenseId;
    private final int slot;
    
    public OffenseKey(int offenseId, int slot) {
        this.offenseId = offenseId;
        this.slot = slot;
    }
}
