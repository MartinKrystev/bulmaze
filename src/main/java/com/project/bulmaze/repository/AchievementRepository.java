package com.project.bulmaze.repository;

import com.project.bulmaze.model.entity.AchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
    Optional<AchievementEntity> findByName(String name);
}
