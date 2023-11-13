package com.project.bulmaze.model.dto;

public class ClueDTO {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public ClueDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClueDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
