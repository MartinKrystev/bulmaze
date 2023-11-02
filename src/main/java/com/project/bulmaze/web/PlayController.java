package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.QuestionDTO;
import com.project.bulmaze.model.dto.UserDTO;
import com.project.bulmaze.service.AnswerService;
import com.project.bulmaze.service.QuestionService;
import com.project.bulmaze.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PlayController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    public PlayController(QuestionService questionService, AnswerService answerService, UserService userService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
    }

    @GetMapping("/play")
    public String getQuestion(Model model, Principal principal) {
        UserDTO user = this.userService.findByUsername(principal.getName());
        List<QuestionDTO> allQuestions = this.questionService.allQuestions();

        if (user.getUserProgress() < allQuestions.size()) {
            QuestionDTO question = this.questionService.askQuestion(principal);
            model.addAttribute("user", user);
            model.addAttribute("question", question);
            model.addAttribute("allQuestions", allQuestions);
        } else {
            return "congrats";
        }
        return "questions";
    }


}
