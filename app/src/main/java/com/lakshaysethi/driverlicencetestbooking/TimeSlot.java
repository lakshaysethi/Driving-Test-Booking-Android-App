package com.lakshaysethi.driverlicencetestbooking;

public class TimeSlot {
    private int startTime;
    private int duration = 1;


    public TimeSlot(int startTime, int duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
