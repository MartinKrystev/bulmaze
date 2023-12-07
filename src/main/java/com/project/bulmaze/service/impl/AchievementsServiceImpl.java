package com.project.bulmaze.service.impl;

import com.project.bulmaze.repository.AchievementRepository;
import com.project.bulmaze.service.AchievementsService;
import org.springframework.stereotype.Service;

@Service
public class AchievementsServiceImpl implements AchievementsService {
    private final AchievementRepository achievementRepository;

    public AchievementsServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }


}
