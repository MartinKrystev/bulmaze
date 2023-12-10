package com.project.bulmaze.scheduler;

import com.project.bulmaze.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InquiryCheckScheduler {
    private final InquiryService inquiryService;

    @Autowired
    public InquiryCheckScheduler(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

//    @Scheduled(fixedRate = 10_000) // currently 10 seconds --> or: 3_600_000
    @Scheduled(cron = "* 0 13 * * *") // at 13:00 every day
    public void sendInquiryMails() {
        this.inquiryService.sendInquiryMails();
    }
}
