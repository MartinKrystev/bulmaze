package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.AddInquiryDTO;
import com.project.bulmaze.model.entity.InquiryEntity;
import com.project.bulmaze.repository.InquiryRepository;
import com.project.bulmaze.service.InquiryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InquiryServiceImplTest {

    @Autowired
    private InquiryService inquiryServiceTest;

    @Mock
    private InquiryRepository inquiryRepository;

    @BeforeEach
    void setUp() {
        inquiryRepository.deleteAll();
        inquiryServiceTest = new InquiryServiceImpl(inquiryRepository);
    }

    @AfterEach
    void tearDown() {
        inquiryRepository.deleteAll();
    }

    @Test
    void testAddInquiry() {
        InquiryEntity testInquiryEntity = new InquiryEntity()
                .setName("testName")
                .setEmail("test@email.com")
                .setSubject("testSubject")
                .setMessage("testMessage")
                .setReviewed(false);

        AddInquiryDTO testInquiryDTO = new AddInquiryDTO()
                .setName("testName")
                .setEmail("test@email.com")
                .setSubject("testSubject")
                .setMessage("testMessage");

        when(inquiryRepository.findByEmailAndSubject("test@email.com", "testSubject"))
                .thenReturn(Optional.of(testInquiryEntity));

        when(inquiryRepository.save(Mockito.any(InquiryEntity.class)))
                .thenAnswer(e -> e.getArguments()[0]);

        Optional<InquiryEntity> optInquiry =
                inquiryRepository.findByEmailAndSubject(testInquiryDTO.getEmail(), testInquiryDTO.getSubject());

        Assertions.assertTrue(optInquiry.isPresent());
        Assertions.assertEquals(inquiryRepository.save(testInquiryEntity), testInquiryEntity);
        Assertions.assertEquals(testInquiryEntity.getName(), "testName");
        Assertions.assertEquals(testInquiryEntity.getEmail(), "test@email.com");
        Assertions.assertEquals(testInquiryEntity.getSubject(), "testSubject");
        Assertions.assertEquals(testInquiryEntity.getMessage(), "testMessage");

        Assertions.assertFalse(inquiryServiceTest.addInquiry(testInquiryDTO));
    }

    @Test
    void testAddInquiryToSave() {
        AddInquiryDTO testInquiryDTO = new AddInquiryDTO()
                .setName("testName")
                .setEmail("test@email.com")
                .setSubject("testSubject")
                .setMessage("testMessage");

        Assertions.assertTrue(inquiryServiceTest.addInquiry(testInquiryDTO));
    }

    @Test
    void testSendInquiryMails() {
        InquiryEntity inquiryEntity = new InquiryEntity()
                .setName("testName")
                .setEmail("test@email.com")
                .setSubject("testSubject")
                .setMessage("testMessage")
                .setReviewed(false);

        List<InquiryEntity> all = inquiryRepository.findAll();
        when(inquiryRepository.findAll())
                .thenReturn(List.of(inquiryEntity));

        Assertions.assertTrue(all.isEmpty());

        inquiryServiceTest.sendInquiryMails();
    }

}
