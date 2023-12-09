package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.model.entity.UserRoleEntity;
import com.project.bulmaze.model.enums.UserRoleEnum;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.repository.UserRoleRepository;
import com.project.bulmaze.service.UserRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplTest {
    private static final Long TEST_ID = 1L;

    private UserRoleService testService;

    @Mock
    private UserRoleRepository testUserRoleRepository;

    @Mock
    private UserRepository testUserRepository;

    @BeforeEach
    public void setUp() {
        testService = new UserRoleServiceImpl(testUserRoleRepository, testUserRepository);
    }

    @Test
    public void testMakeAdmin() {
        UserEntity testUser = new UserEntity()
                .setUserProgress(TEST_ID)
                .setFirstName("testName")
                .setLastName("testLast")
                .setPassword("testPass")
                .setEmail("test@test.com");

        UserRoleEntity testRole = new UserRoleEntity()
                .setId(TEST_ID)
                .setRole(UserRoleEnum.ADMIN);


        lenient().when(testUserRepository.findById(TEST_ID))
                .thenReturn(Optional.of(testUser));

        when(testUserRoleRepository.findUserRoleEntityByRole(UserRoleEnum.ADMIN))
                .thenReturn(Optional.of(testRole));
        when(testUserRoleRepository.findUserRoleEntityByRole(UserRoleEnum.MODERATOR))
                .thenReturn(Optional.of(testRole));

        testService.makeAdmin(1L);
        testService.makeModerator(1L);
    }

    @Test
    public void testFindAllRoles() {
        this.testService.findAllRoles();
    }

    @Test
    public void testDeleteRoles() {
        this.testService.deleteRoles(TEST_ID);
    }
}