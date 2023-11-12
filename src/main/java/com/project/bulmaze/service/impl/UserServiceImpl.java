package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.*;
import com.project.bulmaze.model.entity.AnswerEntity;
import com.project.bulmaze.model.entity.GivenAnswerEntity;
import com.project.bulmaze.model.entity.QuestionEntity;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.repository.AnswerRepository;
import com.project.bulmaze.repository.GivenAnswerRepository;
import com.project.bulmaze.repository.QuestionRepository;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final GivenAnswerRepository givenAnswerRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, AnswerRepository answerRepository, QuestionRepository questionRepository, GivenAnswerRepository givenAnswerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.givenAnswerRepository = givenAnswerRepository;
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
                .setUserProgress(0L)
                .setRoles(new ArrayList<>());

        this.userRepository.save(user);
        return true;
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            UserDTO userDTO = this.modelMapper.map(byUsername.get(), UserDTO.class);

            List<GivenAnswerEntity> givenAnswers = byUsername.get().getGivenAnswers();

            if (userDTO.getUserProgress() == null) {
                userDTO.setUserProgress(0L);
            }

//            List<AnswerDTO> answerDTOS = new ArrayList<>();
            //TODO: can't find AnswerEntity correctly

//            List<QuestionEntity> answeredQuestions = byUsername.get().getAnsweredQuestions();
//            for (QuestionEntity a : answeredQuestions) {
//                givenAnswers.add(this.modelMapper.map(a.getAnswer(), GivenAnswerDTO.class));
//            }

//            givenAnswers.forEach(a -> {
//                givenAnswers.add(this.modelMapper.map(a, GivenAnswerDTO.class));
//            });

            List<GivenAnswerDTO> givenAnswersDTO = givenAnswers
                    .stream()
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

            Optional<QuestionEntity> optQuestion = this.questionRepository.findByName(answer.getName());
            if (optQuestion.isPresent()) {
                QuestionEntity questionEntity = optQuestion.get();

                userEntity.addAnsweredQuestion(questionEntity);
                userEntity.setScore(userEntity.getScore() + 1);

                Optional<GivenAnswerEntity> byDescription = this.givenAnswerRepository.findByDescription(answer.getDescription());
                if (byDescription.isPresent()) {
                    userEntity.addGivenAnswer(byDescription.get());
                } else {
                    userEntity.addGivenAnswer(new GivenAnswerEntity().setDescription(answer.getDescription()));
                }

                this.userRepository.save(userEntity);
            }
        }
    }

    @Override
    public void addWrongAnswer(UserDTO user, AnswerDTO answer) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(user.getUsername());

        if (byUsername.isPresent()) {
            UserEntity userEntity = byUsername.get();

            Optional<GivenAnswerEntity> byDescription = this.givenAnswerRepository.findByDescription(answer.getDescription());
            if (byDescription.isPresent()) {
                userEntity.addGivenAnswer(byDescription.get());
            } else {
                userEntity.addGivenAnswer(new GivenAnswerEntity().setDescription(answer.getDescription()));
            }

            this.userRepository.save(userEntity);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> all = this.userRepository.findAll();
        List<UserDTO> userDTOList = all
                .stream()
                .map(u -> modelMapper.map(u, UserDTO.class))
                .toList();

        return userDTOList;
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
