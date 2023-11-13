package com.project.bulmaze.model.dto;

import java.util.UUID;

public class QuestionDTO {
    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    private String ask;
    private String imageUrl;
    private String correctAnswer;

    public Long getId() {
        return id;
    }

    public QuestionDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public QuestionDTO setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuestionDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QuestionDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAsk() {
        return ask;
    }

    public QuestionDTO setAsk(String ask) {
        this.ask = ask;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public QuestionDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionDTO setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}
