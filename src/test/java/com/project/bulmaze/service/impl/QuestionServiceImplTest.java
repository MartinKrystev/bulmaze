package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.*;
import com.project.bulmaze.model.entity.QuestionEntity;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.repository.*;
import com.project.bulmaze.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    private final Long TEST_ID = 1L;
    private QuestionService testService;

    @Mock
    private QuestionRepository mockQuestionRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private OptionsRepository mockOptionsRepository;
    @Mock
    private AnswerRepository mockAnswerRepository;
    @Mock
    private ClueRepository mockClueRepository;
    @Mock
    private StoryRepository mockStoryRepository;

    @Mock
    private ModelMapper mockMapper;

    @BeforeEach
    public void setUp() {
        testService = new QuestionServiceImpl(mockQuestionRepository, mockUserRepository, mockMapper,
                mockOptionsRepository, mockAnswerRepository, mockClueRepository, mockStoryRepository);
    }

    @Test
    public void testAskQuestion() {
        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "testName";
            }
        };
        UserEntity testUser = new UserEntity()
                .setUserProgress(TEST_ID)
                .setFirstName("testName")
                .setLastName("testLast")
                .setPassword("testPass")
                .setEmail("test@test.com");

        when(mockUserRepository.findByUsername(principal.getName()))
                .thenReturn(Optional.of(testUser));

        testService.askQuestion(principal);

        Assertions.assertEquals(1L, testUser.getUserProgress());
    }

    @Test
    public void testAllQuestions() {
        QuestionEntity testDTO1 = new QuestionEntity()
                .setId(TEST_ID)
                .setName("testName")
                .setAsk("testAsk")
                .setDescription("testDescription")
                .setImageUrl("testImageUrl");
        QuestionEntity testDTO2 = new QuestionEntity()
                .setId(2L)
                .setName("testName2")
                .setAsk("testAsk2")
                .setDescription("testDescription2")
                .setImageUrl("testImageUrl2");
        List<QuestionEntity> testAll = new ArrayList<>();
        testAll.add(testDTO1);

        lenient().when(mockQuestionRepository.findAll())
                .thenReturn(testAll);

        testService.allQuestions();
    }

    @Test
    public void testAskNextQuestion() {
        QuestionEntity testQuestion = new QuestionEntity()
                .setId(TEST_ID)
                .setName("testName")
                .setAsk("testAsk")
                .setDescription("testDescription")
                .setImageUrl("testImageUrl");
        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "testName";
            }
        };

        lenient().when(mockQuestionRepository.findById(1L))
                .thenReturn(Optional.of(testQuestion));

        testService.askNextQuestion(1L, principal);
    }

    @Test
    public void testAddNewQuestion() {
        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "testName";
            }
        };
        QuestionEntity testQuestion = new QuestionEntity()
                .setId(TEST_ID)
                .setName("testName")
                .setAsk("testAsk")
                .setDescription("testDescription")
                .setImageUrl("testImageUrl");

        AddQuestionDTO testQuestionDTO = new AddQuestionDTO()
                .setName("testName")
                .setAsk("testAsk")
                .setDescription("testDescription")
                .setImageUrl("testImageUrl");

        AddOptionsDTO testOptionDTO = new AddOptionsDTO()
                .setFirst("testDescription")
                .setSecond("testSecond")
                .setThird("testThird")
                .setFourth("testDescription");

        AddAnswerDTO testAnswerDTO = new AddAnswerDTO()
                .setName("testName")
                .setDescription("testDescription");

        AddClueDTO testClueDTO = new AddClueDTO()
                .setName("testName")
                .setDescription("testDescription");

        AddStoryDTO testStoryDTO = new AddStoryDTO()
                .setName("testName")
                .setDescription("testDescription");

        AddQuestionWrapperDTO testWrapper = new AddQuestionWrapperDTO();
        testWrapper.setQuestion(testQuestionDTO)
                .setOptions(testOptionDTO)
                .setAnswer(testAnswerDTO)
                .setClue(testClueDTO)
                .setStory(testStoryDTO);

        UserEntity testUser = new UserEntity()
                .setUserProgress(TEST_ID)
                .setFirstName("testName")
                .setLastName("testLast")
                .setPassword("testPass")
                .setEmail("test@test.com");

        when(mockUserRepository.findByUsername(principal.getName()))
                .thenReturn(Optional.of(testUser));

        testService.addNewQuestion(testWrapper, principal);
    }


}