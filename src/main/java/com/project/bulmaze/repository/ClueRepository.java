package com.project.bulmaze.repository;

import com.project.bulmaze.model.entity.ClueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClueRepository extends JpaRepository<ClueEntity, Long> {
}
