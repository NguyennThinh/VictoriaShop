package com.commercer.shop.dao;

import com.commercer.shop.model.Cart;
import com.commercer.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class CartDaoImpl implements CartService {

    @Autowired
    private WebClient webClient;

    @Override
    public Cart viewCart(String token) {


        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cart/items")
                        .build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToMono(Cart.class)
                .block();

    }

    @Override
    public boolean addToCart(int idProduct, int qty, String token) {

        WebClient.ResponseSpec response = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/cart/add-to-cart")
                        .queryParam("pid", idProduct)
                        .queryParam("qty", qty)
                        .build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
        HttpStatusCode status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();

      return   status.is2xxSuccessful();
    }

    @Override
    public boolean removeItemCart(int id, String token) {
        WebClient.ResponseSpec response = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/cart/item/remove")
                        .queryParam("item-id", id)
                        .build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
        HttpStatusCode status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
        return status.is2xxSuccessful();
    }
}
