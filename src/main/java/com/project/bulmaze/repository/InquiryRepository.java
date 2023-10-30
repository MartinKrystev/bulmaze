package com.project.bulmaze.repository;

import com.project.bulmaze.model.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    Optional<InquiryEntity> findByEmailAndSubject(String email, String subject);
}
