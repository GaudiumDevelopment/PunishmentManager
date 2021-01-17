package me.superbiebel.punishmentmanager.utils;

import lombok.Getter;

public class TimeDuration {
    @Getter
    private long wholeYears = 0;
    @Getter
    private long wholeMonths = 0;
    @Getter
    private long wholeDays = 0;
    @Getter
    private long wholeHours = 0;
    @Getter
    private long wholeMinutes = 0;
    @Getter
    private long wholeseconds = 0;
    @Getter
    private long totalMillis = 0;

    public static final int millisInSec = 1000;
    public static final int millisInMin = millisInSec * 60;
    public static final int millisInHour = millisInMin * 60;
    public static final int millisInDay = millisInHour * 24;
    public static final long millisInMonth = millisInDay * 30L;
    public static final long millisInYear = millisInMonth * 12L;


    public TimeDuration(long totalMillis) {
        this.totalMillis = totalMillis;
        if (this.totalMillis > millisInYear) {
            wholeYears = (long) Math.floor(this.totalMillis/millisInYear);
            this.totalMillis = this.totalMillis - (wholeYears * millisInYear);
        }
        if (this.totalMillis > millisInMonth) {
            wholeMonths = (long) Math.floor(this.totalMillis/millisInMonth);
            this.totalMillis = this.totalMillis - (wholeMonths * millisInMonth);
        }
        if (this.totalMillis > millisInDay) {
            wholeDays = (long) Math.floor(this.totalMillis/millisInDay);
            this.totalMillis = this.totalMillis - (wholeDays * millisInDay);
        }
        if (this.totalMillis > millisInHour) {
            wholeHours = (long) Math.floor(this.totalMillis/millisInHour);
            this.totalMillis = this.totalMillis - (wholeHours * millisInHour);
        }
        if (this.totalMillis > millisInMin) {
            wholeMinutes = (long) Math.floor(this.totalMillis/millisInMin);
            this.totalMillis = this.totalMillis - (wholeMinutes * millisInMin);
        }
        if (this.totalMillis > millisInSec) {
            wholeseconds = (long) Math.floor(this.totalMillis/millisInSec);
            this.totalMillis = this.totalMillis - (wholeseconds * millisInSec);
        }
    }

    public enum TIME {
        YEAR,MONTH,DAY,HOUR,MINUTE,SECOND
    }
    
}
