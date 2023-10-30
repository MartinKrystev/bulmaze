package com.project.bulmaze.repository;

import com.project.bulmaze.model.entity.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<SeasonEntity, Long> {
}
