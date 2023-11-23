package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.ClueDTO;
import com.project.bulmaze.model.entity.ClueEntity;
import com.project.bulmaze.repository.ClueRepository;
import com.project.bulmaze.service.ClueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClueServiceImpl implements ClueService {
    private final ClueRepository clueRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClueServiceImpl(ClueRepository clueRepository, ModelMapper modelMapper) {
        this.clueRepository = clueRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClueDTO getClue(Long id) {
        Optional<ClueEntity> byId = this.clueRepository.findById(id);
        return byId.map(clueEntity -> this.modelMapper.map(clueEntity, ClueDTO.class)).orElse(null);
    }

    @Override
    public List<ClueDTO> getAllClues() {
        List<ClueEntity> all = this.clueRepository.findAll();
        return all.stream().map(c -> modelMapper.map(c, ClueDTO.class)).toList();
    }
}
