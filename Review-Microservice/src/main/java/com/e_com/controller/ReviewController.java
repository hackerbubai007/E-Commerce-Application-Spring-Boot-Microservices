package com.e_com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.request.ReviewRequest;
import com.e_com.response.ReviewResponse;
import com.e_com.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ReviewResponse createReview(@RequestBody @Valid ReviewRequest request) {
        return reviewService.createReview(request);
    }

    @GetMapping("/product/{id}")
    public List<ReviewResponse> getReviews(@PathVariable Long id) {
        return reviewService.getReviewsByProduct(id);
    }
}
