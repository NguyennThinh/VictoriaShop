package com.commercer.shop.dao;

import com.commercer.shop.model.User;
import com.commercer.shop.model.dto.Account;
import com.commercer.shop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthDaoImpl implements AuthService {

    @Autowired
    private WebClient webClient;
    private ClientResponse.Headers headers;

    @Override
    public User register(Account account) {

        User result =webClient.post().uri("/auth/register")
                .body(Mono.just(account), Account.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.CONFLICT)){

                     return  null;
                    }else  {
                        return clientResponse.bodyToMono(User.class);
                    }
                })
                .block();


        return result;
    }

    @Override
    public String Login(String email, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password",password);


        String token = webClient.post().uri("/auth/login")
                        .body(Mono.just(map), Map.class)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();


        return token;
    }

    @Override
    public boolean logout() {

        WebClient.ResponseSpec response = webClient
                .post()
                .uri("/auth/logout")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
        HttpStatusCode status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();

        return status.is2xxSuccessful();
    }
}
