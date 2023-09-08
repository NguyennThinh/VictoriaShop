package com.commercer.shop.dao;

import com.commercer.shop.model.Review;
import com.commercer.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ReviewDaoImpl implements ReviewService {

    @Autowired
    private WebClient webClient;

    @Override
    public List<Review> findReviewByProductId(int productId) {


        return webClient.get().uri(uriBuilder -> uriBuilder.path("/product/reviews")
                .queryParam("product", productId)
                .build())
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }
}
