package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.ListUserRoleDTO;
import com.project.bulmaze.model.dto.UserDTO;
import com.project.bulmaze.model.entity.UserRoleEntity;
import com.project.bulmaze.repository.UserRoleRepository;
import com.project.bulmaze.service.UserRoleService;
import com.project.bulmaze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {
    private final UserRoleService userRoleService;
    private final UserService userService;

    @Autowired
    public RoleController(UserRoleService userRoleService, UserService userService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @GetMapping("/change-roles")
    public String getChangeRoles(Model model) {

        ListUserRoleDTO allRoles = this.userRoleService.findAllRoles();
        List<UserDTO> allUsers = this.userService.getAllUsers();

        model.addAttribute("allRoles", allRoles);
        model.addAttribute("allUsers", allUsers);
        return "change-roles";
    }

    @GetMapping("/users/choose-roles{id}")
    public String chooseChange(@PathVariable Long id, Model model) {
        UserDTO userDTO = this.userService.findById(id);

        model.addAttribute("userDTO", userDTO);

        return "choose-roles";
    }

    @GetMapping("/users/change-roles/admin{id}")
    public String makeAdmin(@PathVariable Long id) {
        this.userRoleService.makeAdmin(id);
        return "roles-changed";
    }

    @GetMapping("/users/change-roles/moderator{id}")
    public String makeModerator(@PathVariable Long id) {
        this.userRoleService.makeModerator(id);
        return "roles-changed";
    }

    @GetMapping("/users/change-roles/delete-roles{id}")
    public String deleteRoles(@PathVariable Long id) {
        this.userRoleService.deleteRoles(id);
        return "roles-changed";
    }


}
