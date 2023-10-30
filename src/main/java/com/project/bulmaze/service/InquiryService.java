package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AddInquiryDTO;
import com.project.bulmaze.model.entity.InquiryEntity;

import java.util.Optional;

public interface InquiryService {
    boolean addInquiry(AddInquiryDTO addInquiry);
    Optional<InquiryEntity> findByEmailAndSubject(String email, String subject);
}
