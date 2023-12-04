package com.project.bulmaze.service.impl;

import com.project.bulmaze.repository.GivenAnswerRepository;
import com.project.bulmaze.service.GivenAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GivenAnswerServiceImpl implements GivenAnswerService {
    private final GivenAnswerRepository givenAnswerRepository;

    @Autowired
    public GivenAnswerServiceImpl(GivenAnswerRepository givenAnswerRepository) {
        this.givenAnswerRepository = givenAnswerRepository;
    }

}
