package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.UserDTO;
import com.project.bulmaze.model.dto.UserRegisterDTO;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO == null) {
            return false;
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        String username = userRegisterDTO.getUsername();
        if (this.userRepository.findByUsername(username).isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity()
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setCountry(userRegisterDTO.getCountry() != null ? userRegisterDTO.getCountry() : "No Country Selected")
                .setUsername(username)
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .setScore(0)
                .setRoles(new ArrayList<>());

        this.userRepository.save(user);
        return true;
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return this.modelMapper.map(byUsername, UserDTO.class);
        }

        return null;
    }
}
