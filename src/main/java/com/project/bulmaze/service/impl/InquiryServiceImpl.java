package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.AddInquiryDTO;
import com.project.bulmaze.model.entity.InquiryEntity;
import com.project.bulmaze.repository.InquiryRepository;
import com.project.bulmaze.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;

    @Autowired
    public InquiryServiceImpl(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @Override
    public boolean addInquiry(AddInquiryDTO addInquiry) {
        Optional<InquiryEntity> optInquiry = this.inquiryRepository.findByEmailAndSubject(addInquiry.getEmail(), addInquiry.getSubject());
        if (optInquiry.isPresent()) {
            return false;
        }

        InquiryEntity toSave = new InquiryEntity()
                .setName(addInquiry.getName())
                .setEmail(addInquiry.getEmail())
                .setSubject(addInquiry.getSubject())
                .setMessage(addInquiry.getMessage());
        this.inquiryRepository.save(toSave);
        return true;
    }

    @Override
    public Optional<InquiryEntity> findByEmailAndSubject(String email, String subject) {
        return this.inquiryRepository.findByEmailAndSubject(email, subject);
    }
}
