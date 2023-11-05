package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.OptionsDTO;
import com.project.bulmaze.model.entity.OptionsEntity;
import com.project.bulmaze.repository.OptionsRepository;
import com.project.bulmaze.service.OptionsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OptionsServiceImpl implements OptionsService {
    private final OptionsRepository optionsRepository;
    private final ModelMapper mapper;

    @Autowired
    public OptionsServiceImpl(OptionsRepository optionsRepository, ModelMapper mapper) {
        this.optionsRepository = optionsRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OptionsDTO> getAllOptions() {
        List<OptionsEntity> allOptions = this.optionsRepository.findAll();
        List<OptionsDTO> allOptionsDTO = new ArrayList<>();
        allOptions.forEach(o -> {
            allOptionsDTO.add(this.mapper.map(o, OptionsDTO.class));
        });
        return allOptionsDTO;
    }

    @Override
    public OptionsDTO optionsCurrQuestion(Long userProgress) {
        Optional<OptionsEntity> optOptions = this.optionsRepository.findById(userProgress);
        if (optOptions.isPresent()) {
            return this.mapper.map(optOptions, OptionsDTO.class);
        }
        return null;
    }
}
