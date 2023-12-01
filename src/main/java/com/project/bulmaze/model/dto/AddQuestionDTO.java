package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddQuestionDTO {

    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters!")
    private String name;

    @Size(min = 2, max = 5000, message = "Description length must be between 2 and 5000 characters!")
    private String description;

    @Size(min = 2, max = 50, message = "Ask length must be between 2 and 50 characters!")
    private String ask;

    @NotBlank(message = "The URL image must be at least 10 characters!")
    private String imageUrl;

    public String getName() {
        return name;
    }

    public AddQuestionDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddQuestionDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAsk() {
        return ask;
    }

    public AddQuestionDTO setAsk(String ask) {
        this.ask = ask;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddQuestionDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
