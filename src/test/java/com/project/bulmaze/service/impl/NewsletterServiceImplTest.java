package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.NewsletterDTO;
import com.project.bulmaze.model.entity.NewsletterEntity;
import com.project.bulmaze.repository.NewsletterRepository;
import com.project.bulmaze.service.NewsletterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NewsletterServiceImplTest {

    @Autowired
    private NewsletterService newsletterServiceTest;

    @Mock
    private NewsletterRepository newsletterRepository;

    @BeforeEach
    void setUp() {
        newsletterServiceTest = new NewsletterServiceImpl(newsletterRepository);
    }

    @Test
    void testAddToNewsletter() {
        NewsletterEntity testEntity = new NewsletterEntity()
                .setEmail("test@test.com");

        NewsletterDTO testDTO = new NewsletterDTO()
                .setEmail("testDTO@test.com");


        Assertions.assertEquals(testEntity.getEmail(), "test@test.com");
        Assertions.assertTrue(newsletterServiceTest.addToNewsletter(testDTO));
    }

    @Test
    void testAddToNewsletterNotPresent() {
        Optional<NewsletterEntity> optByEmail = newsletterRepository.findByEmail("testNotValid@test.com");
        Assertions.assertTrue(optByEmail.isEmpty());
    }

}