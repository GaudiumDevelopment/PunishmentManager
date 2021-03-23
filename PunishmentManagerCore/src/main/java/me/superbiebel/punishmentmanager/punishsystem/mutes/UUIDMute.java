package me.superbiebel.punishmentmanager.punishsystem.mutes;

import java.util.UUID;

public class UUIDMute {
    private UUID uuid;
    private long beginTime;
    private long endTime; //negative if permanent
    private long duration;//negative if permanent
    private String scope;
}
