package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AnswerDTO;

import java.util.List;

public interface AnswerService {
    AnswerDTO getAnswer(Long id);
    boolean compareAnswers(String correctAnswer, String givenAnswer);
    List<AnswerDTO> getAllGivenAnswers(String name);
}
