package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AnswerDTO;
import com.project.bulmaze.model.dto.UserDTO;
import com.project.bulmaze.model.dto.UserRegisterDTO;

public interface UserService {

    boolean register(UserRegisterDTO userRegisterDTO);
    UserDTO findByUsername(String username);

    void addAnswer(UserDTO user, AnswerDTO answer);
}
