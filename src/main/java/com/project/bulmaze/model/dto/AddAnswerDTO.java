package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Size;

public class AddAnswerDTO {

    private String name;
    @Size(min = 1, message = "Answer length must be at least 1 character!")
    private String description;

    public String getName() {
        return name;
    }

    public AddAnswerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddAnswerDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
