package com.project.bulmaze.model.dto;

import java.util.List;

public class UserScoreboardWrapperDTO {
    private List<UserScoreboardDTO> allUsers;

    public List<UserScoreboardDTO> getAllUsers() {
        return allUsers;
    }

    public UserScoreboardWrapperDTO setAllUsers(List<UserScoreboardDTO> allUsers) {
        this.allUsers = allUsers;
        return this;
    }
}
