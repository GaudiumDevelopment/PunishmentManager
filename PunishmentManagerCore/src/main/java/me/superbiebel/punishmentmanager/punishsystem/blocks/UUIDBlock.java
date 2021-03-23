package me.superbiebel.punishmentmanager.punishsystem.blocks;

import java.util.UUID;

public class UUIDBlock {
    private UUID uuid;
    private long beginTime;
    private long endTime;//negative if permanent
    private long duration;//negative if permanent
    private String scope;
}
