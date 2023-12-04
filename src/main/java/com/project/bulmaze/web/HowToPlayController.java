package com.project.bulmaze.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HowToPlayController {

    @GetMapping("how-to-play")
    public String getHowToPlay() {
        return "how-to-play";
    }
}
