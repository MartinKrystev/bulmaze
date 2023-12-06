package com.project.bulmaze.model.dto;

public class UserScoreboardDTO {
    private String username;
    private int score;
    private long time;
    private String timeFormatted;


    public String getUsername() {
        return username;
    }

    public UserScoreboardDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getScore() {
        return score;
    }

    public UserScoreboardDTO setScore(int score) {
        this.score = score;
        return this;
    }

    public long getTime() {
        return time;
    }

    public UserScoreboardDTO setTime(long time) {
        this.time = time;
        return this;
    }

    public String getTimeFormatted() {
        long hours;
        long minutes;
        long seconds;
        hours = time / 3600;
        minutes = (time % 3600) / 60;
        seconds = time % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public UserScoreboardDTO setTimeFormatted(String timeFormatted) {
        this.timeFormatted = timeFormatted;
        return this;
    }
}
