package com.e_com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.e_com.client.ProductClientReview;
import com.e_com.client.response.ProductResponse;
import com.e_com.entity.ReviewEntity;
import com.e_com.repositories.ReviewRepository;
import com.e_com.request.ReviewRequest;
import com.e_com.response.ReviewResponse;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductClientReview productClient;

    public ReviewService(ReviewRepository reviewRepository, ProductClientReview productClient) {
        this.reviewRepository = reviewRepository;
        this.productClient = productClient;
    }

    public ReviewResponse createReview(ReviewRequest request) {

        // Validate product exists
        ProductResponse product = productClient.getProduct(request.getProductId());

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        ReviewEntity review = new ReviewEntity();
        review.setProductId(request.getProductId());
        review.setUserId(request.getUserId());
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        ReviewEntity saved = reviewRepository.save(review);

        return mapToResponse(saved);
    }

    public List<ReviewResponse> getReviewsByProduct(Long productId) {

        return reviewRepository.findByProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ReviewResponse mapToResponse(ReviewEntity review) {

        ReviewResponse res = new ReviewResponse();
        res.setReviewId(review.getReviewId());
        res.setProductId(review.getProductId());
        res.setUserId(review.getUserId());
        res.setRating(review.getRating());
        res.setComment(review.getComment());
        res.setCreatedAt(review.getCreatedAt());

        return res;
    }
}

