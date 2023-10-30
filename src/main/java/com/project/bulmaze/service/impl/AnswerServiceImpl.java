package com.project.bulmaze.service.impl;

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
}
