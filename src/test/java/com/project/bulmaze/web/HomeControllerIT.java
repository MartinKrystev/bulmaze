package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.AddInquiryDTO;
import com.project.bulmaze.model.dto.NewsletterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testPostNewsLetter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testPostNewsLetterCorrect() throws Exception {
        NewsletterDTO addNewsletterDTO = new NewsletterDTO()
                .setEmail("test@test.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/")
                        .flashAttr("addNewsletterDTO", addNewsletterDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/newsletter-signed"));
    }


    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testGetContact() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @Test
    public void testContactSent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contact-sent"))
                .andExpect(status().isFound());
    }

    @Test
    public void testPostContactCorrect() throws Exception {
        AddInquiryDTO addInquiry = new AddInquiryDTO()
                .setName("testName")
                .setEmail("test@mail.com")
                .setMessage("testMessage")
                .setSubject("testSubject");

        mockMvc.perform(MockMvcRequestBuilders.post("/contact")
                        .flashAttr("addInquiry", addInquiry)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/contact"));
    }

    @Test
    public void testGetAbout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

    @Test
    public void testGetNewsletter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/newsletter-signed").with(csrf()))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

}