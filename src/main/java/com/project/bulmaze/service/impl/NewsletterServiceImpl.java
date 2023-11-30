package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.NewsletterDTO;
import com.project.bulmaze.model.entity.NewsletterEntity;
import com.project.bulmaze.repository.NewsletterRepository;
import com.project.bulmaze.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsletterServiceImpl implements NewsletterService {
    private final NewsletterRepository newsletterRepository;

    @Autowired
    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public boolean addToNewsletter(NewsletterDTO addNewsletterDTO) {
        Optional<NewsletterEntity> byEmail = this.newsletterRepository.findByEmail(addNewsletterDTO.getEmail());
        if (byEmail.isPresent()) {
            //Email is already registered for the newsletter
            return true;
        }

        NewsletterEntity newsletterEntity = new NewsletterEntity().setEmail(addNewsletterDTO.getEmail());
        this.newsletterRepository.save(newsletterEntity);
        return true;
    }
}
