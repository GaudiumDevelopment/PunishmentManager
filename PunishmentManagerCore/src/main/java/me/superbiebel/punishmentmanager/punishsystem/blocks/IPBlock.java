package me.superbiebel.punishmentmanager.punishsystem.blocks;

import java.net.InetAddress;

public class IPBlock {
    private InetAddress ipAddress;
    private long beginTime;
    private long endTime;//negative if permanent
    private long duration;//negative if permanent
    private String scope;
}
