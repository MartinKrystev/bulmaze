package com.project.bulmaze.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermsConditionController {

    @GetMapping("/terms-conditions")
    public String terms() {
        return "terms-conditions";
    }
}
