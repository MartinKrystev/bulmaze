package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.AddFaqDTO;
import com.project.bulmaze.model.dto.EditFaqDTO;
import com.project.bulmaze.model.entity.FaqEntity;

import java.util.List;

public interface FaqService {
    List<FaqEntity> allFaqs();
    EditFaqDTO findById(Long id);
    boolean saveEditedFAQ(EditFaqDTO editFaqDTO);
    void deleteFAQ(Long id);
    boolean addNewFaq(AddFaqDTO addFaqDTO);
}
