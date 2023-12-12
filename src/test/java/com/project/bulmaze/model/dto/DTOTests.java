package com.project.bulmaze.model.dto;

import com.project.bulmaze.model.enums.UserRoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DTOTests {

    @Test
    public void testFirstAnswerDTO() {
        FirstAnswerDTO firstAnswerDTO = new FirstAnswerDTO()
                .setAnswer(1L);

        Assertions.assertEquals(1L, firstAnswerDTO.getAnswer());
    }

    @Test
    public void testGivenAnswerWrapperDTO() {
        GivenAnswerDTO givenAnswerDTO = new GivenAnswerDTO().setDescription("testDescription");
        GivenAnswerWrapperDTO givenAnswerWrapperDTO = new GivenAnswerWrapperDTO()
                .setAnswers(List.of(givenAnswerDTO));

        Assertions.assertEquals("testDescription", givenAnswerWrapperDTO.getAnswers().get(0).getDescription());
    }

    @Test
    public void testListUserRoleDTO() {
        UserRoleDTO userRoleDTO = new UserRoleDTO().setRole(UserRoleEnum.USER);
        ListUserRoleDTO listUserRoleDTO = new ListUserRoleDTO().setRoles(List.of(userRoleDTO));

        Assertions.assertEquals(UserRoleEnum.USER, listUserRoleDTO.getRoles().get(0).getRole());
    }

    @Test
    public void testOptionsDTO() {
        OptionsDTO optionsDTO = new OptionsDTO()
                .setFirst("testFirst")
                .setSecond("testSecond")
                .setSecond("testThird")
                .setSecond("testFourth");

        Assertions.assertEquals("testFirst", optionsDTO.getFirst());
    }

    @Test
    public void testReviewDTO() {
        ReviewDTO reviewDTO = new ReviewDTO()
                .setReview("testReview")
                .setDate(LocalDate.now())
                .setApproved(true)
                .setStars(5)
                .setUsername("admin");

        Assertions.assertEquals("testReview", reviewDTO.getReview());
    }

    @Test
    public void testUserLoginDTO() {
        UserLoginDTO userLoginDTO = new UserLoginDTO()
                .setUsername("testUsername")
                .setPassword("testPassword");

        Assertions.assertEquals("testUsername", userLoginDTO.getUsername());
    }

    @Test
    public void testUserDTO() {
        UserDTO userDTO = new UserDTO()
                .setId(1L)
                .setCountry("Bulgaria")
                .setReviewSent(true);

        Assertions.assertEquals(1l, userDTO.getId());
        Assertions.assertEquals("Bulgaria", userDTO.getCountry());
        Assertions.assertTrue( userDTO.isReviewSent());
    }
}
