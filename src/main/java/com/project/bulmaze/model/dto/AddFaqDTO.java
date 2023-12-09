package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Size;

public class AddFaqDTO {
    @Size(min = 10, message = "Question length must be at least 10 symbols!")
    private String question;
    @Size(min = 10, message = "Answer length must be at least 10 symbols!")
    private String answer;

    public String getQuestion() {
        return question;
    }

    public AddFaqDTO setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public AddFaqDTO setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}
