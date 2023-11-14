package com.project.bulmaze.web;

import com.project.bulmaze.email.EmailSender;
import com.project.bulmaze.model.dto.UserRegisterDTO;
import com.project.bulmaze.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/users/register")
    public String postRegister(@Valid @ModelAttribute(name = "userRegisterDTO") UserRegisterDTO userRegisterDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !this.userService.register(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }

        EmailSender.newUserRegisteredEmail();
        return "login";
    }

    @GetMapping("/users/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/users/login-error")
    public String loginError(@ModelAttribute(name = "username") String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", true);
        return "login";
    }

    @PostMapping("/users/logout")
    public String logout() {
        return "index";
    }


    //Model Attributes
    @ModelAttribute(name = "userRegisterDTO")
    public UserRegisterDTO initUserRegistrationDTO() {
        return new UserRegisterDTO();
    }
    
}
