package com.project.bulmaze.model.dto;

public class StoryDTO {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public StoryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StoryDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
