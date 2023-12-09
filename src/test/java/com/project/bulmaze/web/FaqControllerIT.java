package com.project.bulmaze.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FaqControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFAQ() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/faq")
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSaveEditedFAQ() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/faq/save{id}", 1L)
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testPostSaveEditedFAQ() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/faq/save{id}", 1L)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteFAQ() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/faq/delete{id}", 1L)
                .with(csrf()))
                .andExpect(status().is2xxSuccessful());
    }

}
