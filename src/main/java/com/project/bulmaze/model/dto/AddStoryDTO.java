package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Size;

public class AddStoryDTO {

    private String name;
    @Size(min = 1, message = "Story length must be at least 50 characters!")
    private String description;

    public String getName() {
        return name;
    }

    public AddStoryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddStoryDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
