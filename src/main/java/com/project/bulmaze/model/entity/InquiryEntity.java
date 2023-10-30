package com.project.bulmaze.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "inquiries")
public class InquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    public Long getId() {
        return id;
    }

    public InquiryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public InquiryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InquiryEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public InquiryEntity setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public InquiryEntity setMessage(String message) {
        this.message = message;
        return this;
    }
}
