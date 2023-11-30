package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.*;
import com.project.bulmaze.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class PlayController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;
    private final OptionsService optionsService;
    private final ClueService clueService;

    public PlayController(QuestionService questionService,
                          AnswerService answerService,
                          UserService userService,
                          OptionsService optionsService,
                          ClueService clueService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
        this.optionsService = optionsService;
        this.clueService = clueService;
    }

    @GetMapping("/play{userProgress}")
    public String getTEST(@PathVariable Long userProgress) {
        return "redirect:/play";
    }

    @GetMapping("/play")
    public String getQuestion(Model model, Principal principal) {
        UserDTO userDTO = this.userService.findByUsername(principal.getName());
        List<QuestionDTO> allQuestions = this.questionService.allQuestions();

        if (userDTO.getUserProgress() < allQuestions.size()) {
            QuestionDTO questionDTO = this.questionService.askNextQuestion(userDTO.getUserProgress() + 1, principal);
            OptionsDTO optionsDTO = this.optionsService.optionsCurrQuestion(userDTO.getUserProgress() + 1);
            ClueDTO clueDTO = this.clueService.getClue(userDTO.getUserProgress() + 1);

            model.addAttribute("userDTO", userDTO);
            model.addAttribute("allQuestions", allQuestions);
            model.addAttribute("question", questionDTO);
            model.addAttribute("options", optionsDTO);
            model.addAttribute("clueDTO", clueDTO);
        } else {
            return "congrats";
        }
        return "play";
    }

    @PostMapping("/play")
    public String postQuestion(@Valid @ModelAttribute(name = "answerDTO") AnswerDTO answerDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("answerDTO", answerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.answerDTO", bindingResult);
            return "redirect:/500";
        }

        UserDTO user = this.userService.findByUsername(principal.getName());
        AnswerDTO answer = this.answerService.getAnswer(user.getUserProgress() + 1);

        if (this.answerService.compareAnswers(answer.getDescription(), answerDTO.getDescription())) {
            this.userService.addCorrectAnswer(user, answerDTO);
        } else {
            this.userService.addWrongAnswer(user, answerDTO);
        }
        return "redirect:/play";
    }

    //Model Attributes
    @ModelAttribute(name = "answerDTO")
    public AnswerDTO initAnswerDTO() {
        return new AnswerDTO();
    }
}
