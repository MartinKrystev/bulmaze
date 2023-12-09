package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.EditFaqDTO;
import com.project.bulmaze.model.entity.FaqEntity;
import com.project.bulmaze.repository.FaqRepository;
import com.project.bulmaze.service.FaqService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public EditFaqDTO findById(Long id) {
        FaqEntity faqEntity = this.faqRepository.findById(id).orElseThrow();

        return new EditFaqDTO()
                .setId(faqEntity.getId())
                .setQuestion(faqEntity.getQuestion())
                .setAnswer(faqEntity.getAnswer());
    }

    @Override
    public boolean saveEditedFAQ(EditFaqDTO editFaqDTO) {
        Optional<FaqEntity> optFaqEntity = this.faqRepository.findById(editFaqDTO.getId());
        if (optFaqEntity.isEmpty()) {
            return false;
        }
        optFaqEntity.get()
                .setId(editFaqDTO.getId())
                .setQuestion(editFaqDTO.getQuestion())
                .setAnswer(editFaqDTO.getAnswer());

        this.faqRepository.save(optFaqEntity.get());
        return true;
    }

    @Override
    public void deleteFAQ(Long id) {
        Optional<FaqEntity> byId = this.faqRepository.findById(id);
        byId.ifPresent(faqEntity -> this.faqRepository.deleteById(faqEntity.getId()));
    }

}
