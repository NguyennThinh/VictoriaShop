package com.commercer.shop.dao;

import com.commercer.shop.model.Review;
import com.commercer.shop.model.User;
import com.commercer.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDaoImpl implements UserService {

    @Autowired
    private WebClient webClient;

    @Override
    public User myProfile( String token) {

        User user = webClient.get().uri("/user")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToMono(User.class);
                    }else {
                        return Mono.empty();
                    }
                }).block();


        return user;
    }

    @Override
    public Review postReview(String comment,int productId, int rating, String token) {

        Map<String, String> body = new HashMap<>();
        body.put("comment", comment);
        body.put("product", String.valueOf(productId));
        body.put("rating", String.valueOf(rating));

        return webClient.post().uri("/user/comment")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Review.class)
                .block();
    }
}
