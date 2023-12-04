package com.project.bulmaze.web;

import com.project.bulmaze.model.entity.FaqEntity;
import com.project.bulmaze.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FaqController {
    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/faq")
    public String getFAQ(Model model) {
        List<FaqEntity> faqEntities = this.faqService.allFaqs();
        model.addAttribute("faqEntities", faqEntities);
        return "faq";
    }

}
