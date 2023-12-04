package com.project.bulmaze.web;

import com.project.bulmaze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestDeleteUserController {

    private final UserService userService;

    @Autowired
    public RestDeleteUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>("The selected user has been deleted!", HttpStatus.OK);

    }

}
