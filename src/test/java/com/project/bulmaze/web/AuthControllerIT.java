package com.project.bulmaze.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRegistration() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/register")
                        .param("firstName", "TestFirst")
                        .param("lastName", "TestLast")
                        .param("email", "TestEmail")
                        .param("username", "TestUsername")
                        .param("password", "TestPassword")
                        .param("confirmPassword", "TestConfirmPassword")
                        .with(csrf())
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testPostRegistration() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .param("firstName", "TestFirst")
                        .param("lastName", "TestLast")
                        .param("email", "TestEmail")
                        .param("username", "TestUsername")
                        .param("password", "TestPassword")
                        .param("confirmPassword", "TestConfirmPassword")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    void testGetLogin() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/login")
                        .param("firstName", "TestFirst")
                        .param("lastName", "TestLast")
                        .param("email", "TestEmail")
                        .param("username", "TestUsername")
                        .param("password", "TestPassword")
                        .param("confirmPassword", "TestConfirmPassword")
                        .with(csrf())
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testPostLoginError() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login-error")
                        .param("username", "username")
        ).andExpect(status().is4xxClientError());
    }

}
