package com.project.bulmaze.service;

import com.project.bulmaze.model.dto.ClueDTO;

import java.util.List;

public interface ClueService {
    ClueDTO getClue(Long id);
    List<ClueDTO> getAllClues();
}
