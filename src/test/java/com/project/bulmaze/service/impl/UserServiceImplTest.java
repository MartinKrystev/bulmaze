package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.*;
import com.project.bulmaze.model.entity.GivenAnswerEntity;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.model.enums.UserRoleEnum;
import com.project.bulmaze.repository.*;
import com.project.bulmaze.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserService testService;

    @Mock
    private UserRepository testUserRepository;
    @Mock
    private PasswordEncoder testPasswordEncoder;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private QuestionRepository testQuestionRepository;
    @Mock
    private GivenAnswerRepository testGivenAnswerRepository;
    @Mock
    private UserRoleRepository testUserRoleRepository;
    @Mock
    private AchievementRepository testAchievementRepository;

    @BeforeEach
    public void setUp() {
        testService = new UserServiceImpl(
                testUserRepository,
                testPasswordEncoder,
                modelMapper,
                testQuestionRepository,
                testGivenAnswerRepository,
                testUserRoleRepository,
                testAchievementRepository);
    }

    @Test
    public void testFindByUsername() {
        GivenAnswerEntity testGivenAnswer = new GivenAnswerEntity()
                .setId(1L)
                .setDescription("testDescription");

        GivenAnswerDTO testAnswerDTO = new GivenAnswerDTO()
                .setDescription("testDescription");

        UserEntity user = new UserEntity()
                .setId(1L)
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setPassword("testPassword")
                .setScore(0)
                .setUserProgress(0L)
                .setRoles(new ArrayList<>())
                .setGivenAnswers(List.of(testGivenAnswer));

        UserRegisterDTO testDTO = new UserRegisterDTO()
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setPassword("testPassword")
                .setConfirmPassword("testPassword");

        UserRoleDTO testUserRoleDTO = new UserRoleDTO()
                .setRole(UserRoleEnum.ADMIN);

        UserDTO testUserDTO = new UserDTO()
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setUserProgress(1L)
                .setScore(0)
                .setRoles(List.of(testUserRoleDTO))
                .setAnswers(List.of(testAnswerDTO));

        when(testUserRepository.findByUsername("testUsername"))
                .thenReturn(Optional.of(user));

        try {
            Assertions.assertNull(testService.findByUsername(testUserDTO.getUsername()));
        } catch (Exception e) {
            System.out.println(e);
        }

        Assertions.assertFalse(testService.register(testDTO));
        Assertions.assertEquals("test@test.com", user.getEmail(), "Email not populated!");
        Assertions.assertFalse(user.isReviewSent());
        Assertions.assertEquals(0L, user.getUserProgress());
    }

    @Test
    public void testAddCorrectAnswer() {
        GivenAnswerDTO testAnswerDTO = new GivenAnswerDTO()
                .setDescription("testDescription");

        UserRoleDTO testUserRoleDTO = new UserRoleDTO()
                .setRole(UserRoleEnum.ADMIN);

        UserDTO testUserDTO = new UserDTO()
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setUserProgress(1L)
                .setScore(0)
                .setRoles(List.of(testUserRoleDTO))
                .setAnswers(List.of(testAnswerDTO));

        GivenAnswerEntity testGivenAnswer = new GivenAnswerEntity()
                .setId(1L)
                .setDescription("testDescription");

        UserEntity user = new UserEntity()
                .setId(1L)
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setPassword("testPassword")
                .setScore(0)
                .setUserProgress(0L)
                .setRoles(new ArrayList<>())
                .setGivenAnswers(List.of(testGivenAnswer));

        AnswerDTO testAnswer = new AnswerDTO()
                .setName("testName")
                .setDescription("testDescription");

        when(testUserRepository.findByUsername("testUsername"))
                .thenReturn(Optional.of(user));

        testService.addCorrectAnswer(testUserDTO, testAnswer);
    }

    @Test
    public void testAddWrongAnswer() {
        AnswerDTO testAnswerDTO = new AnswerDTO()
                .setName("testName")
                .setDescription("testDescription");

        UserRoleDTO testUserRoleDTO = new UserRoleDTO()
                .setRole(UserRoleEnum.ADMIN);
        GivenAnswerDTO testGivenAnswerDTO = new GivenAnswerDTO()
                .setDescription("testDescription");

        UserDTO userDTO = new UserDTO().setFirstName("testFirst")
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setUserProgress(1L)
                .setScore(0)
                .setRoles(List.of(testUserRoleDTO))
                .setAnswers(List.of(testGivenAnswerDTO));

        GivenAnswerEntity testGivenAnswer = new GivenAnswerEntity()
                .setId(1L)
                .setDescription("testDescription");

        UserEntity user = new UserEntity()
                .setId(1L)
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setPassword("testPassword")
                .setScore(0)
                .setUserProgress(0L)
                .setRoles(new ArrayList<>())
                .setGivenAnswers(new ArrayList<>());

        when(testUserRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));
        when(testGivenAnswerRepository.findByDescription(testAnswerDTO.getDescription()))
                .thenReturn(Optional.of(testGivenAnswer));

        testService.addWrongAnswer(userDTO, testAnswerDTO);

    }

    @Test
    public void testGetAllUsers() {
        GivenAnswerEntity testGivenAnswer = new GivenAnswerEntity()
                .setId(1L)
                .setDescription("testDescription");

        UserEntity user = new UserEntity()
                .setId(1L)
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setPassword("testPassword")
                .setScore(0)
                .setUserProgress(0L)
                .setRoles(new ArrayList<>())
                .setGivenAnswers(List.of(testGivenAnswer));

        when(testUserRepository.findAll())
                .thenReturn(List.of(user));

        testService.getAllUsers();
    }

    @Test
    public void testFindById() {
        GivenAnswerEntity testGivenAnswer = new GivenAnswerEntity()
                .setId(1L)
                .setDescription("testDescription");

        UserEntity user = new UserEntity()
                .setId(1L)
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setPassword("testPassword")
                .setScore(0)
                .setUserProgress(0L)
                .setRoles(new ArrayList<>())
                .setGivenAnswers(List.of(testGivenAnswer));

        lenient().when(testUserRepository.findById(1L))
                .thenReturn(Optional.of(user));

        testService.findById(1L);
    }

    @Test
    public void testGetScoreboardResults() {
        GivenAnswerEntity testGivenAnswer = new GivenAnswerEntity()
                .setId(1L)
                .setDescription("testDescription");

        UserEntity user = new UserEntity()
                .setId(1L)
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setUsername("testUsername")
                .setEmail("test@test.com")
                .setPassword("testPassword")
                .setScore(0)
                .setUserProgress(0L)
                .setRoles(new ArrayList<>())
                .setGivenAnswers(List.of(testGivenAnswer));

        when(testUserRepository.findAll())
                .thenReturn(List.of(user));

        testService.getScoreboardResults();
    }

}