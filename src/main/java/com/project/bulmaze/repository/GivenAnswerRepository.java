package com.project.bulmaze.repository;

import com.project.bulmaze.model.entity.GivenAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GivenAnswerRepository extends JpaRepository<GivenAnswerEntity, Long> {
    Optional<GivenAnswerEntity> findByDescription(String description);
}
