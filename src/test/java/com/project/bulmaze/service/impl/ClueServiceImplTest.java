package com.project.bulmaze.service.impl;

import com.project.bulmaze.repository.ClueRepository;
import com.project.bulmaze.service.ClueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class ClueServiceImplTest {

    private ClueService testClueService;

    private ModelMapper testMapper;

    @Mock
    private ClueRepository testClueRepository;

    @BeforeEach
    public void setUp() {
        testClueService = new ClueServiceImpl(testClueRepository, testMapper);
    }

    @Test
    public void testGetAllClues() {
        this.testClueService.getAllClues();
    }

}
