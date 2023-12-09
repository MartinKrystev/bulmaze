package com.project.bulmaze.utils;

import org.springframework.stereotype.Component;

@Component
public class StopWatch {

    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    public long getStartTime() {
        return startTime;
    }

    public StopWatch setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public long getStopTime() {
        return stopTime;
    }

    public StopWatch setStopTime(long stopTime) {
        this.stopTime = stopTime;
        return this;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    public long getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (System.currentTimeMillis() - startTime);
        } else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }

    public long getElapsedTimeSecs() {
        long elapsed;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        } else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }

    public String getElapsedTimeFormatted() {
        long elapsedSeconds;
        long hours;
        long minutes;
        long seconds;
        if (running) {
            elapsedSeconds = ((System.currentTimeMillis() - startTime) / 1000);
        } else {
            elapsedSeconds = ((stopTime - startTime) / 1000);
        }

        hours = elapsedSeconds / 3600;
        minutes = (elapsedSeconds % 3600) / 60;
        seconds = elapsedSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
