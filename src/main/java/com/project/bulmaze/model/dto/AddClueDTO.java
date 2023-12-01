package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Size;

public class AddClueDTO {
    private String name;
    @Size(min = 1, message = "Clue length must be at least 1 character! Provide better clue!")
    private String description;

    public String getName() {
        return name;
    }

    public AddClueDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddClueDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
