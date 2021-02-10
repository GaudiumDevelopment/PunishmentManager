package me.superbiebel.punishmentmanager.utils;

public class TimingUtil {
    private long startTime;
    private long stopTime;


    public TimingUtil start() {
        startTime = System.nanoTime();
        return this;
    }

    public TimingUtil stop() {
        stopTime = System.nanoTime();
        return this;
    }

    public long calcDifferenceInMillis(){
       return calcDifferenceInNanos()/1000000;
    }
    public long calcDifferenceInNanos() {
        return stopTime - startTime;
    }
}
