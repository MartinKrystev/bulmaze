package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.StoryDTO;
import com.project.bulmaze.model.entity.StoryEntity;
import com.project.bulmaze.repository.StoryRepository;
import com.project.bulmaze.service.StoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoryServiceImplTest {

    private StoryService testStoryService;

    @Mock
    private StoryRepository testStoryRepository;

    @BeforeEach
    public void setUp() {
        testStoryService = new StoryServiceImpl(testStoryRepository);
    }

    @Test
    public void testGetStory() {
        StoryDTO testStoryDTO = new StoryDTO()
                .setName("testName")
                .setDescription("testDescription");

        StoryEntity testStoryEntity = new StoryEntity()
                .setId(1L)
                .setName("testName")
                .setDescription("testDescription");

        lenient().when(testStoryRepository.findById(1L))
                .thenReturn(Optional.of(testStoryEntity));

        testStoryService.getStory(1L);
    }

}