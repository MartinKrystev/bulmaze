package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NewsletterDTO {
    @Email(message = "Please enter a valid email address!")
    @NotBlank(message = "Enter your email before you sing up")
    private String email;

    public String getEmail() {
        return email;
    }

    public NewsletterDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
