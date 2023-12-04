package com.project.bulmaze.service.impl;

import com.project.bulmaze.email.NewUserEmailSender;
import com.project.bulmaze.model.dto.*;
import com.project.bulmaze.model.entity.GivenAnswerEntity;
import com.project.bulmaze.model.entity.QuestionEntity;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.model.entity.UserRoleEntity;
import com.project.bulmaze.model.enums.UserRoleEnum;
import com.project.bulmaze.repository.GivenAnswerRepository;
import com.project.bulmaze.repository.QuestionRepository;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.repository.UserRoleRepository;
import com.project.bulmaze.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final GivenAnswerRepository givenAnswerRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper,
                           QuestionRepository questionRepository,
                           GivenAnswerRepository givenAnswerRepository,
                           UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
        this.givenAnswerRepository = givenAnswerRepository;
        this.userRoleRepository = userRoleRepository;
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


        UserRoleEntity userRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();
        UserEntity user = new UserEntity()
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setCountry(userRegisterDTO.getCountry() != null ? userRegisterDTO.getCountry() : "No Country Selected")
                .setUsername(username)
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .setScore(0)
                .setUserProgress(0L)
                .setRoles(List.of(userRole));

        this.userRepository.save(user);
        NewUserEmailSender.newUserRegisteredEmail();
        return true;
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        if (byUsername.isPresent()) {

            UserEntity currUserEntity = byUsername.get();
            if (currUserEntity.getUserProgress() == null) {
                currUserEntity.setUserProgress(0L);
                this.userRepository.save(currUserEntity);
            }

            List<GivenAnswerEntity> givenAnswers = currUserEntity.getGivenAnswers();
            UserDTO userDTO = this.modelMapper.map(currUserEntity, UserDTO.class);

            List<GivenAnswerDTO> givenAnswersDTO = givenAnswers.stream()
                            .map(a -> modelMapper.map(a, GivenAnswerDTO.class))
                            .toList();

            userDTO.setAnswers(givenAnswersDTO);

            return userDTO;
        }
        return null;
    }

    @Override
    public void addCorrectAnswer(UserDTO user, AnswerDTO answer) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(user.getUsername());

        if (byUsername.isPresent()) {
            UserEntity userEntity = byUsername.get();

            Optional<QuestionEntity> optById = this.questionRepository.findById(user.getUserProgress() + 1);
            if (optById.isPresent()) {
                QuestionEntity questionEntity = optById.get();

                userEntity.addAnsweredQuestion(questionEntity);
                userEntity.setScore(userEntity.getScore() + 1);

                addAnswerToUserEntity(answer, userEntity);
                updateUserProgress(userEntity);
            }
        }
    }

    @Override
    public void addWrongAnswer(UserDTO user, AnswerDTO answer) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(user.getUsername());

        if (byUsername.isPresent()) {
            UserEntity userEntity = byUsername.get();

            addAnswerToUserEntity(answer, userEntity);
            updateUserProgress(userEntity);
        }
    }

    @Override
    @Async("asyncExecutor")
    public void deleteUser(Long id) {
        UserEntity byId = this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User was not found!"));
        this.userRepository.delete(byId);
    }

    private void addAnswerToUserEntity(AnswerDTO answer, UserEntity userEntity) {
        Optional<GivenAnswerEntity> byDescription = this.givenAnswerRepository.findByDescription(answer.getDescription());
        if (byDescription.isPresent()) {
            userEntity.addGivenAnswer(byDescription.get());
        } else {
            userEntity.addGivenAnswer(new GivenAnswerEntity().setDescription(answer.getDescription()));
        }
    }

    private void updateUserProgress(UserEntity userEntity) {
        Long currUserProgress = userEntity.getUserProgress();
        userEntity.setUserProgress(currUserProgress + 1);
        this.userRepository.save(userEntity);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> all = this.userRepository.findAll();

        return all
                .stream()
                .map(u -> this.modelMapper.map(u, UserDTO.class))
                .toList();
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<UserEntity> byId = this.userRepository.findById(id);
        if (byId.isPresent()) {
            return this.modelMapper.map(byId, UserDTO.class);
        }
        return null;
    }

    @Override
    public UserScoreboardWrapperDTO getScoreboardResults() {

        List<UserEntity> all = this.userRepository.findAll();
        List<UserScoreboardDTO> allUsersDTO = all
                .stream()
                .map(userEntity -> new UserScoreboardDTO()
                        .setUsername(userEntity.getUsername())
                        .setScore(userEntity.getScore()))
                .sorted((o1, o2) -> o2.getScore() - o1.getScore())
                .toList();

        UserScoreboardWrapperDTO allWrapperDTO = new UserScoreboardWrapperDTO();
        allWrapperDTO.setAllUsers(allUsersDTO);

        return allWrapperDTO;
    }

}
