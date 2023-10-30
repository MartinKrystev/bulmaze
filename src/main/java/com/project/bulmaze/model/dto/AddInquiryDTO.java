package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddInquiryDTO {

    @Size(min = 2, max = 50, message = "Name should be between 1 and 50 characters.")
    private String name;

    @Email(message = "Please enter a valid email address!")
    @NotBlank(message = "Please enter a valid email address!")
    private String email;

    @Size(min = 2, max = 30, message = "Subject should be between 1 and 30 characters.")
    private String subject;

    @Size(min = 20, max = 4000, message = "The message should be between 20 and 4 000 characters!")
    private String message;

    public String getName() {
        return name;
    }

    public AddInquiryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AddInquiryDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public AddInquiryDTO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AddInquiryDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
