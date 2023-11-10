package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.ListUserRoleDTO;
import com.project.bulmaze.model.dto.UserRoleDTO;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.model.entity.UserRoleEntity;
import com.project.bulmaze.model.enums.UserRoleEnum;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.repository.UserRoleRepository;
import com.project.bulmaze.service.UserRoleService;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ListUserRoleDTO findAllRoles() {
        List<UserRoleEntity> all = this.userRoleRepository.findAll();
        ListUserRoleDTO rolesDTO = new ListUserRoleDTO();
        for (UserRoleEntity role : all) {
            UserRoleDTO userRoleDTO = new UserRoleDTO().setRole(role.getRole());
            rolesDTO.addRole(userRoleDTO);
        }

        return rolesDTO;
    }

    @Override
    public void makeAdmin(Long id) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);
        if (optUser.isPresent()) {
            Optional<UserRoleEntity> optRole = this.userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.ADMIN);

            if (optRole.isPresent()) {
                List<UserRoleEntity> currRoles = optUser.get().getRoles();
                boolean hasRole = false;
                for (UserRoleEntity r : currRoles) {
                    if (r.getRole() == UserRoleEnum.ADMIN) {
                        hasRole = true;
                    }
                }
                if (!hasRole) {
                    optUser.get().getRoles().add(optRole.get());
                    this.userRepository.save(optUser.get());
                }
            }
//            adminRole.ifPresent(userRoleEntity -> optUser.get().getRoles().add(userRoleEntity));
//            function style
        }
    }

    @Override
    public void makeModerator(Long id) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);
        if (optUser.isPresent()) {
            Optional<UserRoleEntity> optRole = this.userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.MODERATOR);

            if (optRole.isPresent()) {
                List<UserRoleEntity> currRoles = optUser.get().getRoles();
                boolean hasRole = false;
                for (UserRoleEntity r : currRoles) {
                    if (r.getRole() == UserRoleEnum.MODERATOR) {
                        hasRole = true;
                    }
                }
                if (!hasRole) {
                    optUser.get().getRoles().add(optRole.get());
                    this.userRepository.save(optUser.get());
                }
            }
//            adminRole.ifPresent(userRoleEntity -> optUser.get().getRoles().add(userRoleEntity));
//            function style
        }
    }

    @Override
    public void deleteRoles(Long id) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);
        if (optUser.isPresent()) {

            UserRoleEntity userRole = this.userRoleRepository
                    .findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();

            UserEntity userToSave = optUser.get();
            List<UserRoleEntity> rolesList = new ArrayList<>();
            rolesList.add(userRole);
            userToSave.setRoles(rolesList);

            this.userRepository.save(userToSave);
        }

    }

}
