package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String review;
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

    public boolean isApproved() {
        return isApproved;
    }

    public ReviewEntity setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }
}
