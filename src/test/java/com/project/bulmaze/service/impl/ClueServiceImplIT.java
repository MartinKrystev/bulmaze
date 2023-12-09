package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.ClueDTO;
import com.project.bulmaze.model.entity.ClueEntity;
import com.project.bulmaze.repository.ClueRepository;
import com.project.bulmaze.service.ClueService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClueServiceImplIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClueService clueServiceToTest;

    @Mock
    private ClueRepository mockedClueRepository;

    @Test
    void testGetClue() {
        ClueEntity testClueEntity = new ClueEntity()
                .setId(1L)
                .setName("testName")
                .setDescription("testDescription");

        Optional<ClueEntity> byId = mockedClueRepository.findById(testClueEntity.getId());

        Assertions.assertFalse(byId.isPresent());
    }

    @Test
    void testGetClueDTO() {
        ClueEntity testClueEntity = new ClueEntity()
                .setId(1L)
                .setName("testName")
                .setDescription("testDescription");

        ClueDTO testClueDTO = new ClueDTO()
                .setName("testName2")
                .setDescription("testDescription2");

        when(mockedClueRepository.findById(1L))
                .thenReturn(Optional.of(testClueEntity));

        ClueDTO testClue = clueServiceToTest.getClue(testClueEntity.getId());

        Assertions.assertEquals("testName", testClueEntity.getName() ,"Clue is not populated properly!");


    }

    @Test
    void testGetAllClues() throws Exception {
        ClueEntity testClueEntity = new ClueEntity()
                .setId(1L)
                .setName("testName")
                .setDescription("testDescription");

        List<ClueEntity> testAllClues = mockedClueRepository.findAll();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/questions-all")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());

        when(mockedClueRepository.findAll())
                .thenReturn(List.of(testClueEntity));
    }

}
