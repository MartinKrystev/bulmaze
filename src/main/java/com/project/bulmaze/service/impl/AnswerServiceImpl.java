package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.AnswerDTO;
import com.project.bulmaze.model.dto.FirstAnswerDTO;
import com.project.bulmaze.model.entity.AnswerEntity;
import com.project.bulmaze.repository.AnswerRepository;
import com.project.bulmaze.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (optById.isEmpty()) {
            return false;
        }

//        if (optById.get().getDescription() = firstAnswerDTO.getAnswer())
        return true;
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
}
