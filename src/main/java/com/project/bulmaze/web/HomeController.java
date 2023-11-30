package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.AddInquiryDTO;
import com.project.bulmaze.model.dto.NewsletterDTO;
import com.project.bulmaze.service.InquiryService;
import com.project.bulmaze.service.NewsletterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final NewsletterService newsletterService;
    private final InquiryService inquiryService;

    public HomeController(NewsletterService newsletterService, InquiryService inquiryService) {
        this.newsletterService = newsletterService;
        this.inquiryService = inquiryService;
    }


    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @PostMapping("/")
    public String postNewsletter(@Valid @ModelAttribute(name = "addNewsletterDTO") NewsletterDTO addNewsletterDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !this.newsletterService.addToNewsletter(addNewsletterDTO)) {
            redirectAttributes.addFlashAttribute("addNewsletterDTO", addNewsletterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addNewsletterDTO", bindingResult);
            return "redirect:/";
        }
        return "redirect:/newsletter-signed";
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/read-more")
    public String readMore() {
        return "read-more";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String postContact(@Valid @ModelAttribute(name = "addInquiry") AddInquiryDTO addInquiry,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !this.inquiryService.addInquiry(addInquiry)) {
            redirectAttributes.addFlashAttribute("addInquiry", addInquiry)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addInquiry", bindingResult);
            return "redirect:/contact";
        }
        return "redirect:/contact-sent";
    }

    @GetMapping("/contact-sent")
    public String getContactSent() {
        return "contact-sent";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/newsletter-signed")
    private String getNewsletter() {
        return "newsletter-signed";
    }


    //Model Attributes
    @ModelAttribute(name = "addNewsletterDTO")
    public NewsletterDTO initAddNewsletterDTO() {
        return new NewsletterDTO();
    }

    @ModelAttribute(name = "addInquiry")
    public AddInquiryDTO initAddInquiryDTO() {
        return new AddInquiryDTO();
    }
}
