package com.project.bulmaze.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin@test.com", roles = {"ADMIN"})
    public void testGetTEST() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/play1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/play"));
    }

    @Test
    public void testGetQuestion() throws Exception {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mockMvc.perform(MockMvcRequestBuilders.get("/play")
                        .with(csrf()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }
}