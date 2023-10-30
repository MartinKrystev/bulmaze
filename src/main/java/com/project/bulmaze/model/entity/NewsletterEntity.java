package com.project.bulmaze.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "newsletters")
public class NewsletterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank(message = "Please enter valid email!")
    private String email;

    public Long getId() {
        return id;
    }

    public NewsletterEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NewsletterEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}
