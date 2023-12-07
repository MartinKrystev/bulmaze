package com.project.bulmaze.service.impl;

import com.project.bulmaze.email.NewUserEmailSender;
import com.project.bulmaze.model.dto.*;
import com.project.bulmaze.model.entity.*;
import com.project.bulmaze.model.enums.UserRoleEnum;
import com.project.bulmaze.repository.*;
import com.project.bulmaze.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    private final AchievementRepository achievementRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper,
                           QuestionRepository questionRepository,
                           GivenAnswerRepository givenAnswerRepository,
                           UserRoleRepository userRoleRepository, AchievementRepository achievementRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
        this.givenAnswerRepository = givenAnswerRepository;
        this.userRoleRepository = userRoleRepository;
        this.achievementRepository = achievementRepository;
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
        //TODO: send mail for NEW registration:
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

    @Override
    public void addUserAchievementAndTime(String username, long time) {
        UserEntity user = this.userRepository.findByUsername(username).get();
        List<AchievementEntity> currAchievements = user.getAchievements();
        AchievementEntity achievement = this.achievementRepository.findByName("Sofia Explorer").get();

        currAchievements.add(achievement);
        user.setAchievements(currAchievements);
        user.setTime(time);
        this.userRepository.save(user);
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
                        .setScore(userEntity.getScore())
                        .setTime(userEntity.getTime()))
                .sorted((o1, o2) -> {
                    int result = o2.getScore() - o1.getScore();
                    if (result == 0) {
                        Comparator.comparingLong(UserScoreboardDTO::getTime);
                    }
                    return result;
                })
                .toList();

        UserScoreboardWrapperDTO allWrapperDTO = new UserScoreboardWrapperDTO();
        allWrapperDTO.setAllUsers(allUsersDTO);

        return allWrapperDTO;
    }

}
