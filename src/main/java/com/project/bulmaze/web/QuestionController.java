package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.FirstAnswerDTO;
import com.project.bulmaze.model.dto.QuestionDTO;
import com.project.bulmaze.model.dto.UserDTO;
import com.project.bulmaze.service.AnswerService;
import com.project.bulmaze.service.QuestionService;
import com.project.bulmaze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @Autowired
    public QuestionController(QuestionService questionService, AnswerService answerService, UserService userService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
    }

    @GetMapping("/questions")
    public String getQuestion(Model model, Principal principal) {
        UserDTO user = this.userService.findByUsername(principal.getName());
        List<QuestionDTO> allQuestions = this.questionService.allQuestions();

        model.addAttribute("allQuestions", allQuestions);
        model.addAttribute("user", user);
        return "all-questions";
    }

    @GetMapping("/questions/{id}")
    public String getNextQuestion(@PathVariable("id") Long id) {

        return "redirect:/questions";
    }

    @GetMapping("/questions/6")
    public String congratsPage() {
        return "congrats";
    }

//    @GetMapping("/questions/progress2")
//    public String getProgress() {
//
//        return "progress";
//    }

//    @PostMapping("/questions/progress2")
//    public String postQuestions(@Valid @ModelAttribute(name = "firstAnswerDTO") FirstAnswerDTO firstAnswerDTO,
//                                BindingResult bindingResult,
//                                RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors() || !this.answerService.firstAnswer(firstAnswerDTO)) {
//            return "redirect:/questions";
//        }
//
//        return "redirect:/progress2";
//    }

    //Model Attributes
    @ModelAttribute(name = "firstAnswerDTO")
    public FirstAnswerDTO initFirstAnswerDTO() {
        return new FirstAnswerDTO();
    }
}
