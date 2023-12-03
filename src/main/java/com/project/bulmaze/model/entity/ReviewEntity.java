package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column(columnDefinition = "TEXT")
    private String review;
    @Column
    private int stars;
    @Column
    private LocalDate date;
    @Column(name = "is_approved")
    private boolean isApproved;

    public Long getId() {
        return id;
    }

    public ReviewEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ReviewEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getReview() {
        return review;
    }

    public ReviewEntity setReview(String review) {
        this.review = review;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public ReviewEntity setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public ReviewEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public ReviewEntity setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }
}
