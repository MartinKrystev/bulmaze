package com.project.bulmaze.model.dto;

import java.util.List;

public class GivenAnswerWrapperDTO {
    private List<GivenAnswerDTO> answers;

    public List<GivenAnswerDTO> getAnswers() {
        return answers;
    }

    public GivenAnswerWrapperDTO setAnswers(List<GivenAnswerDTO> answers) {
        this.answers = answers;
        return this;
    }
}
