package com.project.bulmaze.model.dto;

import java.time.LocalDate;

public class ReviewDTO {
    private String username;
    private String review;
    private int stars;
    private LocalDate date;
    private boolean isApproved;

    public String getUsername() {
        return username;
    }

    public ReviewDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getReview() {
        return review;
    }

    public ReviewDTO setReview(String review) {
        this.review = review;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public ReviewDTO setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public ReviewDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public ReviewDTO setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }
}
