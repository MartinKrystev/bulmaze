package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AnswerDTO;
import com.project.bulmaze.model.dto.FirstAnswerDTO;

public interface AnswerService {
    boolean firstAnswer(FirstAnswerDTO firstAnswerDTO);
    AnswerDTO getAnswer(Long id);

    boolean compareAnswers(String correctAnswer, String givenAnswer);
}
