package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AnswerDTO;
import com.project.bulmaze.model.dto.UserDTO;
import com.project.bulmaze.model.dto.UserRegisterDTO;
import com.project.bulmaze.model.dto.UserScoreboardWrapperDTO;

import java.util.List;

public interface UserService {

    boolean register(UserRegisterDTO userRegisterDTO);
    UserDTO findByUsername(String username);
    void addCorrectAnswer(UserDTO user, AnswerDTO answer);
    List<UserDTO> getAllUsers();
    UserDTO findById(Long id);
    UserScoreboardWrapperDTO getScoreboardResults();
}
