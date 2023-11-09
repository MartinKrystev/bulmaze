package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.UserScoreboardWrapperDTO;
import com.project.bulmaze.service.UserService;
import com.project.bulmaze.utils.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScoreBoardController {
    private final UserService userService;

    @Autowired
    public ScoreBoardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/scoreboard")
    public String getScoreBoard(Model model) {

        UserScoreboardWrapperDTO allWrapperDTO = this.userService.getScoreboardResults();
        model.addAttribute("allWrapperDTO", allWrapperDTO);
        model.addAttribute("counter", new Counter());

        return "scoreboard";
    }
}
