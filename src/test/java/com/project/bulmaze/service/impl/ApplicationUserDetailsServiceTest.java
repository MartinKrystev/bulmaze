package com.project.bulmaze.service.impl;

import static org.mockito.Mockito.when;

import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.model.entity.UserRoleEntity;
import com.project.bulmaze.model.enums.UserRoleEnum;
import com.project.bulmaze.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    private ApplicationUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new ApplicationUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("unvalidUser@testtest.com"));
    }

    @Test
    void testUserFoundException() {
        //Arrange
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findUserEntityByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        //Act
        UserDetails userOpt = serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        //Assert
        Assertions.assertNotNull(userOpt);
        Assertions.assertEquals(testUserEntity.getUsername(), userOpt.getUsername(), "Username is not populate correctly");
        Assertions.assertEquals(2, userOpt.getAuthorities().size());
    }

    private static UserEntity createTestUser() {
        return new UserEntity()
                .setUsername("testUser")
                .setFirstName("testFirst")
                .setLastName("testLast")
                .setEmail("unvaliduser@testtest.com")
                .setPassword("testPassword")
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));
    }
}
