package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.EditFaqDTO;
import com.project.bulmaze.model.entity.FaqEntity;
import com.project.bulmaze.repository.FaqRepository;
import com.project.bulmaze.service.FaqService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FaqServiceImplTest {

    private FaqService testFaqService;

    @Mock
    private FaqRepository testFaqRepository;

    @BeforeEach
    void setUp() {
        testFaqService = new FaqServiceImpl(testFaqRepository);
    }

    @Test
    public void testAllFaqs() {
        this.testFaqService.allFaqs();
    }

    @Test
    public void testFindById() {
        FaqEntity testFaqEntity = new FaqEntity()
                .setId(1L)
                .setQuestion("testQuestion")
                .setAnswer("testAnswer");

        when(testFaqRepository.findById(1L))
                .thenReturn(Optional.of(testFaqEntity));

        this.testFaqService.findById(1L);

        Assertions.assertEquals(testFaqRepository.findById(1L).get().getQuestion(), "testQuestion");
    }

    @Test
    public void testSaveEditedFAQ() {
        FaqEntity testFaqEntity = new FaqEntity()
                .setId(1L)
                .setQuestion("testQuestion")
                .setAnswer("testAnswer");

        when(testFaqRepository.findById(1L))
                .thenReturn(Optional.of(testFaqEntity));

        EditFaqDTO testFaqDTO = new EditFaqDTO()
                .setId(1L)
                .setQuestion("testQuestion")
                .setAnswer("testAnswer");
        this.testFaqService.saveEditedFAQ(testFaqDTO);

        Assertions.assertTrue(this.testFaqService.saveEditedFAQ(testFaqDTO));
    }

    @Test
    public void testDeleteFAQ() {
        this.testFaqService.deleteFAQ(1L);
    }
}
