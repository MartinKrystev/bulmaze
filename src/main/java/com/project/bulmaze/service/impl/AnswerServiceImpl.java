package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.AnswerDTO;
import com.project.bulmaze.model.dto.FirstAnswerDTO;
import com.project.bulmaze.model.entity.AnswerEntity;
import com.project.bulmaze.repository.AnswerRepository;
import com.project.bulmaze.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public boolean firstAnswer(FirstAnswerDTO firstAnswerDTO) {
        Optional<AnswerEntity> optById = this.answerRepository.findById(1L);
        return optById.isPresent();
    }

    @Override
    public AnswerDTO getAnswer(Long id) {
        Optional<AnswerEntity> optAnswer = this.answerRepository.findById(id);
        return optAnswer.map(answerEntity -> new AnswerDTO()
                .setName(answerEntity.getName())
                .setDescription(answerEntity.getDescription())).orElse(null);
    }

    @Override
    public boolean compareAnswers(String correctAnswer, String givenAnswer) {
        return correctAnswer.equals(givenAnswer);
    }

    @Override
    public List<AnswerDTO> getAllGivenAnswers(String name) {
        List<AnswerEntity> all = this.answerRepository.findAll();
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        all.forEach(a -> {
            AnswerDTO answerDTO = new AnswerDTO(a.getName(), a.getDescription());
            answerDTOS.add(answerDTO);
        });

        return answerDTOS;
    }
}
