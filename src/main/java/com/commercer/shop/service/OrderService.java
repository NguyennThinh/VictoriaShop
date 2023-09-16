package com.commercer.shop.service;

import com.commercer.shop.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getMyOrder(String token);
}
