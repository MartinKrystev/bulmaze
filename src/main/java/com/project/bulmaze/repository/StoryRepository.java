package com.project.bulmaze.repository;

import com.project.bulmaze.model.entity.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Long> {
    Optional<StoryEntity> findByName(String name);
}
