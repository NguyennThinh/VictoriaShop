package com.commercer.shop.dao;

import com.commercer.shop.model.Category;
import com.commercer.shop.model.Order;
import com.commercer.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDaoImpl implements OrderService {

    @Autowired
    private WebClient webClient;

    @Override
    public List<Order> getMyOrder(String token) {


        return   webClient.get().uri("/order")
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToFlux(Order.class);
                    }else {
                        return Flux.empty();
                    }
                })

                .collectList().block();
    }
}
