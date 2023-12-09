package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.OptionsDTO;
import com.project.bulmaze.repository.OptionsRepository;
import com.project.bulmaze.service.OptionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OptionsServiceImplTest {

    private OptionsService testService;

    @Mock
    private OptionsRepository mockOptionsRepository;

    @Mock
    private ModelMapper mockMapper;

    @BeforeEach
     void setUp() {
        testService = new OptionsServiceImpl(mockOptionsRepository, mockMapper);
    }

    @Test
    public void testGetAllOptions() {
        testService.getAllOptions();
    }

    @Test
    public void testOptionsCurrQuestion() {
        testService.optionsCurrQuestion(1L);
    }


}