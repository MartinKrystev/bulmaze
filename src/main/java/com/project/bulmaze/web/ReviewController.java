package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.AddReviewDTO;
import com.project.bulmaze.model.dto.ReviewDTO;
import com.project.bulmaze.model.dto.UserDTO;
import com.project.bulmaze.service.ReviewService;
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

import java.security.Principal;
import java.util.List;

@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/reviews")
    public String getReviews(Model model) {
        List<ReviewDTO> allReviews = this.reviewService.getAllReviews();
        model.addAttribute("allReviews", allReviews);
        return "reviews";
    }

    @GetMapping("/review/add")
    public String getReview(Principal principal) {
        UserDTO byUsername = this.userService.findByUsername(principal.getName());
        if (byUsername.isReviewSent()) {
            return "review-sent";
        }
        return "review-add";
    }

    @PostMapping("/review/add")
    public String postReview(@Valid @ModelAttribute(name = "addReviewDTO") AddReviewDTO addReviewDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) {

        if (bindingResult.hasErrors() || !this.reviewService.addReview(addReviewDTO, principal)) {
            redirectAttributes.addFlashAttribute("addReviewDTO", addReviewDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addReviewDTO", bindingResult);
            return "redirect:/review/add";
        }
        return "review-sent";
    }


    //Model Attributes
    @ModelAttribute(name = "addReviewDTO")
    public AddReviewDTO initReviewAddDTO() {
        return new AddReviewDTO();
    }
}
