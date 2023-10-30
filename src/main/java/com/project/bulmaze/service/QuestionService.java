package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.QuestionDTO;

import java.security.Principal;
import java.util.List;

public interface QuestionService {
    QuestionDTO askQuestion(Principal principal);
    List<QuestionDTO> allQuestions();
}
