package com.project.bulmaze.service.impl;

import com.project.bulmaze.model.dto.ReviewAddDTO;
import com.project.bulmaze.model.dto.ReviewDTO;
import com.project.bulmaze.model.entity.ReviewEntity;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.repository.ReviewRepository;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<ReviewEntity> all = this.reviewRepository.findAll();
        return all
                .stream()
                .map(r -> this.modelMapper.map(r, ReviewDTO.class))
                .toList();
    }

    @Override
    public boolean addReview(ReviewAddDTO reviewAddDTO, Principal principal) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(principal.getName());
        if (byUsername.isEmpty()) {
            return false;
        }

        ReviewEntity reviewEntity = new ReviewEntity()
                .setReview(reviewAddDTO.getReview())
                .setUsername(principal.getName())
                .setApproved(false);

        byUsername.get().setReviewSent(true);

        this.userRepository.save(byUsername.get());
        this.reviewRepository.save(reviewEntity);
        return true;
    }

    @Override
    public void approveReviews() {
        List<ReviewEntity> all = this.reviewRepository.findAll();
        List<ReviewEntity> approved = all.stream()
                .map(r -> r.setApproved(true))
                .toList();

        this.reviewRepository.saveAll(approved);
    }
}
