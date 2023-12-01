package com.project.bulmaze.model.dto;

import jakarta.validation.Valid;

public class AddQuestionWrapperDTO {
    @Valid
    private AddQuestionDTO question;
    @Valid
    private AddOptionsDTO options;
    @Valid
    private AddAnswerDTO answer;
    @Valid
    private AddClueDTO clue;
    @Valid
    private AddStoryDTO story;

    public AddQuestionDTO getQuestion() {
        return question;
    }

    public AddQuestionWrapperDTO setQuestion(AddQuestionDTO question) {
        this.question = question;
        return this;
    }

    public AddOptionsDTO getOptions() {
        return options;
    }

    public AddQuestionWrapperDTO setOptions(AddOptionsDTO options) {
        this.options = options;
        return this;
    }

    public AddAnswerDTO getAnswer() {
        return answer;
    }

    public AddQuestionWrapperDTO setAnswer(AddAnswerDTO answer) {
        this.answer = answer;
        return this;
    }

    public AddClueDTO getClue() {
        return clue;
    }

    public AddQuestionWrapperDTO setClue(AddClueDTO clue) {
        this.clue = clue;
        return this;
    }

    public AddStoryDTO getStory() {
        return story;
    }

    public AddQuestionWrapperDTO setStory(AddStoryDTO story) {
        this.story = story;
        return this;
    }
}
