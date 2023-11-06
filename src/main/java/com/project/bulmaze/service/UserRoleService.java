package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.ListUserRoleDTO;

public interface UserRoleService {
    ListUserRoleDTO findAllRoles();
    void makeAdmin(Long id);
    void makeModerator(Long id);

    void deleteRoles(Long id);
}
