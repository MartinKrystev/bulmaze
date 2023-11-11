package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.*;
import com.project.bulmaze.service.AnswerService;
import com.project.bulmaze.service.QuestionService;
import com.project.bulmaze.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping("/all-questions")
    public String getQuestion(Model model, Principal principal,
                              @PageableDefault(
                                      size = 1,
                                      sort = "id"
                              ) Pageable pageable) {
        UserDTO user = this.userService.findByUsername(principal.getName());
        List<QuestionDTO> allQuestions = this.questionService.allQuestions();
        Page<QuestionDTO> allQuestionsPage = this.questionService.getAllQuestions(pageable);

        int totalPages = allQuestionsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("allQuestions", allQuestions);
        model.addAttribute("allQuestionsPage", allQuestionsPage);
        model.addAttribute("user", user);
        return "all-questions";

        //TODO: fix the PAGINATION...
    }

    @GetMapping("/questions/{id}")
    public String getNextQuestion(@PathVariable("id") Long id) {
        return "redirect:/questions";
    }

    @GetMapping("/questions/6")
    public String congratsPage() {
        return "congrats";
    }

    @GetMapping("/add-question")
    public String getAddQuestion() {
        return "add-question";
    }

    @PostMapping("/add-question")
    public String postAddQuestion(@Valid @ModelAttribute(name = "addQuestionWrapperDTO") AddQuestionWrapperDTO addQuestionWrapperDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal) {
        if (bindingResult.hasErrors() || !this.questionService.addNewQuestion(addQuestionWrapperDTO, principal)) {
            redirectAttributes.addFlashAttribute("addQuestionWrapperDTO", addQuestionWrapperDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addQuestionWrapperDTO", bindingResult);
            return "redirect:/add-question";
        }
        return "redirect:/question-added";
    }

    @GetMapping("question-added")
    public String getQuestionAdded() {
        return "/question-added";
    }


    //Model Attributes
    @ModelAttribute(name = "firstAnswerDTO")
    public FirstAnswerDTO initFirstAnswerDTO() {
        return new FirstAnswerDTO();
    }

    @ModelAttribute(name = "addQuestionDTO")
    public AddQuestionDTO initAddQuestionDTO() {
        return new AddQuestionDTO();
    }
    @ModelAttribute(name = "addOptionsDTO")
    public AddOptionsDTO initAddOptionsDTO() {
        return new AddOptionsDTO();
    }
    @ModelAttribute(name = "addQuestionWrapperDTO")
    public AddQuestionWrapperDTO initAddQuestionWrapperDTO() {
        return new AddQuestionWrapperDTO();
    }


}
