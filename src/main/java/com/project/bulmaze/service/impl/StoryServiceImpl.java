package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.StoryDTO;
import com.project.bulmaze.model.entity.StoryEntity;
import com.project.bulmaze.repository.StoryRepository;
import com.project.bulmaze.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {
    private final StoryRepository storyRepository;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public StoryDTO getStory(Long id) {
        Optional<StoryEntity> byId = this.storyRepository.findById(id);
        return byId.map(storyEntity -> new StoryDTO()
                .setName(storyEntity.getName())
                .setDescription(storyEntity.getDescription())).orElse(null);
    }
}
