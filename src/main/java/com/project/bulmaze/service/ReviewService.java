package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.ReviewAddDTO;
import com.project.bulmaze.model.dto.ReviewDTO;

import java.security.Principal;
import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAllReviews();
    boolean addReview(ReviewAddDTO reviewAddDTO, Principal principal);
    void approveReviews();
}
