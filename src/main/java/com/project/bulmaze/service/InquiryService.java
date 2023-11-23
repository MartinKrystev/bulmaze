package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AddInquiryDTO;


public interface InquiryService {
    boolean addInquiry(AddInquiryDTO addInquiry);
    void sendInquiryMails();
}
