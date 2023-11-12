package com.project.bulmaze.scheduler;

import com.project.bulmaze.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewsApprovalScheduler {

    private final ReviewService reviewService;

    @Autowired
    public ReviewsApprovalScheduler(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Scheduled(fixedRate = 60_000)
    public void approveReviews() {
        System.out.println("Triggered scheduler at " + LocalDateTime.now());
        this.reviewService.approveReviews();
    }
}
