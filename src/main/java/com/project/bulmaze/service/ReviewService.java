package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AddReviewDTO;
import com.project.bulmaze.model.dto.ReviewDTO;

import java.security.Principal;
import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAllReviews();
    boolean addReview(AddReviewDTO addReviewDTO, Principal principal);
    void approveReviews();
}
