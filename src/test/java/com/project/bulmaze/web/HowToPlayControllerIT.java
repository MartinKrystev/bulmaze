package com.project.bulmaze.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class HowToPlayControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHowToPlay() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/how-to-play"))
                .andExpect(status().isOk())
                .andExpect(view().name("how-to-play"));
    }

}