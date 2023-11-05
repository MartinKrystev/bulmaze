package com.project.bulmaze.model.dto;

public class AnswerDTO {
    private String name;
    private String description;

    public AnswerDTO() {
    }

    public AnswerDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public AnswerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AnswerDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
