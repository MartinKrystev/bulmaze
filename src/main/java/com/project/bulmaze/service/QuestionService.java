package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AddQuestionDTO;
import com.project.bulmaze.model.dto.AddQuestionWrapperDTO;
import com.project.bulmaze.model.dto.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface QuestionService {
    QuestionDTO askQuestion(Principal principal);
    List<QuestionDTO> allQuestions();
    Page<QuestionDTO> getAllQuestions(Pageable pageable);
    QuestionDTO askNextQuestion(Long userProgress, Principal principal);
    boolean addNewQuestion(AddQuestionWrapperDTO addQuestionWrapperDTO, Principal principal);
}
