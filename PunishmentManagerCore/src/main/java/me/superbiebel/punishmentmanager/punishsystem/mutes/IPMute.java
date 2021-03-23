package me.superbiebel.punishmentmanager.punishsystem.mutes;

import java.net.InetAddress;

public class IPMute {
    private InetAddress ipAddress;
    private long beginTime;
    private long endTime;//negative if permanent
    private long duration;//negative if permanent
    private String scope;
}
