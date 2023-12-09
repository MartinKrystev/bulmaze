package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.AddReviewDTO;
import com.project.bulmaze.model.entity.ReviewEntity;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.repository.ReviewRepository;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    private ReviewService testService;
    @Mock
    private ReviewRepository testReviewRepository;
    @Mock
    private ModelMapper testModelMapper;
    @Mock
    private UserRepository testUserRepository;

    @Captor
    private ArgumentCaptor<ReviewEntity> userEntityArgumentCaptor;

    @BeforeEach
    public void setUp() {
        testService = new ReviewServiceImpl(testReviewRepository, testModelMapper, testUserRepository);
    }

    @Test
    public void testAddReview() {
        UserEntity testUser = new UserEntity()
                .setUserProgress(1L)
                .setFirstName("testName")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setPassword("testPass")
                .setEmail("test@test.com");

        AddReviewDTO testReviewDTO = new AddReviewDTO()
                .setDate(LocalDate.now())
                .setReview("testReview")
                .setStars(5);

        Principal testPrincipal = new Principal() {
            @Override
            public String getName() {
                return "testName";
            }
        };

        ReviewEntity testReviewEntity = new ReviewEntity()
                .setId(2L)
                .setReview("testReview")
                .setApproved(true)
                .setDate(LocalDate.now())
                .setUsername("testUsername");

        lenient().when(testUserRepository.findByUsername(testPrincipal.getName()))
                .thenReturn(Optional.of(testUser));

        testService.addReview(testReviewDTO, testPrincipal);

        Assertions.assertNotNull(testUserRepository.findByUsername("testName"));

        Mockito.verify(testReviewRepository).save(userEntityArgumentCaptor.capture());
        ReviewEntity savedReview = userEntityArgumentCaptor.getValue();
        Assertions.assertEquals(testReviewDTO.getReview(), savedReview.getReview());
    }

}