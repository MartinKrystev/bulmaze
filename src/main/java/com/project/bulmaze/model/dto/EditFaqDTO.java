package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Size;

public class EditFaqDTO {
    private Long id;
    @Size(min = 10, message = "Question length must be at least 10 symbols!")
    private String question;
    @Size(min = 10, message = "Answer length must be at least 10 symbols!")
    private String answer;

    public Long getId() {
        return id;
    }

    public EditFaqDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public EditFaqDTO setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public EditFaqDTO setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}
