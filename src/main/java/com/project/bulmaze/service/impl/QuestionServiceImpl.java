package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.AddQuestionWrapperDTO;
import com.project.bulmaze.model.dto.QuestionDTO;
import com.project.bulmaze.model.entity.*;
import com.project.bulmaze.repository.*;
import com.project.bulmaze.service.QuestionService;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final OptionsRepository optionsRepository;
    private final AnswerRepository answerRepository;
    private final ClueRepository clueRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, UserRepository userRepository, ModelMapper mapper, OptionsRepository optionsRepository, AnswerRepository answerRepository, ClueRepository clueRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.optionsRepository = optionsRepository;
        this.answerRepository = answerRepository;
        this.clueRepository = clueRepository;
    }


    @Override
    public QuestionDTO askQuestion(Principal principal) {
        String username = principal.getName();
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);

        if (byUsername.isPresent()) {
            if (byUsername.get().getUserProgress() == null) {
                byUsername.get().setUserProgress(0L);
            }

            Long currUserProgress = byUsername.get().getUserProgress();
            Optional<QuestionEntity> optById = this.questionRepository.findById(currUserProgress + 1);

//            currUserProgress = byUsername.get().getUserProgress();
            byUsername.get().setUserProgress(currUserProgress + 1L);
            this.userRepository.save(byUsername.get());

            QuestionDTO question = new QuestionDTO();
            if (optById.isPresent()) {
                byUsername.get().addAnsweredQuestion(optById.get());
                question = this.mapper.map(optById.get(), QuestionDTO.class);
                question.setUuid(UUID.randomUUID());
            }
            return question;
        }
        return null;
    }

    @Override
    public List<QuestionDTO> allQuestions() {
        List<QuestionEntity> all = this.questionRepository.findAll();
        List<QuestionDTO> allQuestionDTO = new ArrayList<>();
        all.forEach(q -> {
            QuestionDTO dto = new QuestionDTO()
                    .setUuid(UUID.randomUUID())
                    .setName(q.getName())
                    .setDescription(q.getDescription())
                    .setAsk(q.getAsk())
                    .setImageUrl(q.getImageUrl());
            allQuestionDTO.add(dto);
        });
        return allQuestionDTO;
    }

    @Override
    public Page<QuestionDTO> getAllQuestions(Pageable pageable) {
        return questionRepository
                .findAll(pageable)
                .map(this::mapAsSummary);
    }

    @Override
    @Transactional
    public QuestionDTO askNextQuestion(Long userProgress, Principal principal) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(principal.getName());

        if (byUsername.isPresent()) {
            byUsername.get().setUserProgress(userProgress);
            this.userRepository.save(byUsername.get());
        }

        Optional<QuestionEntity> optQuestion = this.questionRepository.findById(userProgress);
        return optQuestion.map(questionEntity -> new QuestionDTO()
                        .setId(questionEntity.getId())
                        .setUuid(UUID.randomUUID())
                        .setName(questionEntity.getName())
                        .setDescription(questionEntity.getDescription())
                        .setAsk(questionEntity.getAsk())
                        .setImageUrl(questionEntity.getImageUrl()))
                .orElse(null);
    }

    @Override
    public boolean addNewQuestion(AddQuestionWrapperDTO addQuestionWrapperDTO, Principal principal) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(principal.getName());
        if (byUsername.isPresent()) {

            Optional<QuestionEntity> byNameQuestion = this.questionRepository.findByName(addQuestionWrapperDTO.getQuestion().getName());
            if (byNameQuestion.isPresent()) {
                return false;
            }

            QuestionEntity newQuestion = new QuestionEntity()
                    .setName(addQuestionWrapperDTO.getQuestion().getName())
                    .setDescription(addQuestionWrapperDTO.getQuestion().getDescription())
                    .setAsk(addQuestionWrapperDTO.getQuestion().getAsk())
                    .setImageUrl(addQuestionWrapperDTO.getQuestion().getImageUrl());
            this.questionRepository.save(newQuestion);

            OptionsEntity newOptions = new OptionsEntity()
                    .setFirst(addQuestionWrapperDTO.getOptions().getFirst())
                    .setSecond(addQuestionWrapperDTO.getOptions().getSecond())
                    .setThird(addQuestionWrapperDTO.getOptions().getThird())
                    .setFourth(addQuestionWrapperDTO.getOptions().getFourth());
            this.optionsRepository.save(newOptions);

            AnswerEntity newAnswer = new AnswerEntity()
                    .setName(addQuestionWrapperDTO.getQuestion().getName())
                    .setDescription(addQuestionWrapperDTO.getAnswer().getDescription());
            this.answerRepository.save(newAnswer);

            ClueEntity newClue = new ClueEntity()
                    .setName(addQuestionWrapperDTO.getQuestion().getName())
                    .setDescription(addQuestionWrapperDTO.getClue().getDescription());
            this.clueRepository.save(newClue);

            return true;
        }
        return false;
    }

    private QuestionDTO mapAsSummary(QuestionEntity questionEntity) {
        Optional<AnswerEntity> optAnswer = this.answerRepository.findById(questionEntity.getId());
        if (optAnswer.isPresent()) {
            AnswerEntity answerEntity = optAnswer.get();

            return new QuestionDTO()
                    .setId(questionEntity.getId())
                    .setUuid(UUID.randomUUID())
                    .setName(questionEntity.getName())
                    .setDescription(questionEntity.getDescription())
                    .setAsk(questionEntity.getAsk())
                    .setImageUrl(questionEntity.getImageUrl())
                    .setCorrectAnswer(answerEntity.getDescription());
        }

        return null;
    }
}
