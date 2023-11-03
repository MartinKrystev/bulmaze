package com.project.bulmaze.repository;

import com.project.bulmaze.model.entity.OptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<OptionsEntity, Long> {
}
