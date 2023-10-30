package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.QuestionDTO;
import com.project.bulmaze.model.entity.QuestionEntity;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.repository.QuestionRepository;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, UserRepository userRepository, ModelMapper mapper) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public QuestionDTO askQuestion(Principal principal) {
        String username = principal.getName();
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        Long currUserProgress = 0L;
        if (byUsername.isPresent()) {
            currUserProgress = byUsername.get().getUserProgress();
            byUsername.get().setUserProgress(currUserProgress + 1L);
            this.userRepository.save(byUsername.get());
        }

//        assert currUserProgress != null;
        Optional<QuestionEntity> optById = this.questionRepository.findById(currUserProgress);
        QuestionDTO question = new QuestionDTO();
        if (optById.isPresent()) {
            question = this.mapper.map(optById.get(), QuestionDTO.class);
        }

        return question;
    }

    @Override
    public List<QuestionDTO> allQuestions() {
        List<QuestionEntity> all = this.questionRepository.findAll();
        List<QuestionDTO> allQuestionDTO = new ArrayList<>();
        all.forEach(q -> {
            QuestionDTO dto = this.mapper.map(q, QuestionDTO.class);
            allQuestionDTO.add(dto);
        });

        return allQuestionDTO;
    }
}
