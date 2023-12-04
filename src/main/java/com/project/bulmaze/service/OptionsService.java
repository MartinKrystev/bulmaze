package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.OptionsDTO;

import java.util.List;

public interface OptionsService {
    List<OptionsDTO> getAllOptions();
    OptionsDTO optionsCurrQuestion(Long userProgress);
}
