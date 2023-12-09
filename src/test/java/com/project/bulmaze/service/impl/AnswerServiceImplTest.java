package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.AnswerDTO;
import com.project.bulmaze.model.entity.AnswerEntity;
import com.project.bulmaze.repository.AnswerRepository;
import com.project.bulmaze.service.AnswerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    private AnswerService answerService;
    @Mock
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
        answerRepository.deleteAll();
        answerService = new AnswerServiceImpl(answerRepository);
    }

    @Test
    void testGetAnswer() {
        AnswerEntity testAnswer = new AnswerEntity()
                .setId(1L)
                .setName("test")
                .setDescription("testDescription");
        AnswerDTO testDTO = new AnswerDTO()
                .setName("test")
                .setDescription("testDescription");

        when(answerRepository.findById(testAnswer.getId()))
                .thenReturn(Optional.of(testAnswer));

        answerService.getAnswer(1L);

        Assertions.assertEquals(Optional.of(testAnswer), answerRepository.findById(testAnswer.getId()));
    }

    @Test
    void testCompareAnswers() {
        String first = "first";
        String firstEqual = "first";
        String second = "second";

        answerService.compareAnswers(first, second);

        Assertions.assertTrue(answerService.compareAnswers(first, firstEqual));
        Assertions.assertFalse(answerService.compareAnswers(first, second));
    }

    @Test
    public void testGetAllGivenAnswers() {
        this.answerService.getAllGivenAnswers("username");
    }
}