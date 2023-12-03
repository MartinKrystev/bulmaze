package com.project.bulmaze.model.dto;

public class UserScoreboardDTO {
    private String username;
    private int score;


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


}
