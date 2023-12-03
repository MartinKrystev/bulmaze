package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddReviewDTO {
    @Size(min = 5, message = "The review should be at least 5 symbols long.")
    private String review;
    private int stars;
    private LocalDate date;

    public String getReview() {
        return review;
    }

    public AddReviewDTO setReview(String review) {
        this.review = review;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public AddReviewDTO setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public AddReviewDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
