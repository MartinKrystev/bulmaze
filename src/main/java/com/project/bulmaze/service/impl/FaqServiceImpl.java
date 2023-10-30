package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.entity.FaqEntity;
import com.project.bulmaze.repository.FaqRepository;
import com.project.bulmaze.service.FaqService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {
    private final FaqRepository faqRepository;

    public FaqServiceImpl(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public List<FaqEntity> allFaqs() {
        return this.faqRepository.findAll();
    }
}
