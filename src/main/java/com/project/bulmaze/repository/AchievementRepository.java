package com.project.bulmaze.repository;

import com.project.bulmaze.model.entity.AchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
}
