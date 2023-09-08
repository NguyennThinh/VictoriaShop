package com.commercer.shop.service;


import com.commercer.shop.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findReviewByProductId(int productId);
}
