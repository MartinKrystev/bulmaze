package com.project.bulmaze.service.impl;

import com.project.bulmaze.email.NewInquiryEmailSender;
import com.project.bulmaze.email.NewUserEmailSender;
import com.project.bulmaze.model.dto.AddInquiryDTO;
import com.project.bulmaze.model.entity.InquiryEntity;
import com.project.bulmaze.repository.InquiryRepository;
import com.project.bulmaze.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .setMessage(addInquiry.getMessage())
                .setReviewed(false);
        this.inquiryRepository.save(toSave);
        return true;
    }

    @Override
    public void sendInquiryMails() {
        List<InquiryEntity> all = this.inquiryRepository.findAll();
        if (!all.isEmpty()) {
            List<InquiryEntity> unReviewed = all.stream().filter(i -> !i.isReviewed()).toList();
            if (!unReviewed.isEmpty()) {
                //sending mail for new inquiry
                NewInquiryEmailSender.sendInquiryMails(unReviewed);
            }

            List<InquiryEntity> reviewed = unReviewed.stream().map(i -> i.setReviewed(true)).toList();
            this.inquiryRepository.saveAll(reviewed);
        }
    }
}
