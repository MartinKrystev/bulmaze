package com.project.bulmaze.scheduler;

import com.project.bulmaze.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReviewsApprovalScheduler {

    private final ReviewService reviewService;

    @Autowired
    public ReviewsApprovalScheduler(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Scheduled(fixedRate = 3_600_000)
    public void approveReviews() {
        this.reviewService.approveReviews();
    }
}
