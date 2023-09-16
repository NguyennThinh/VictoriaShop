package com.commercer.shop.service;

import com.commercer.shop.model.Cart;

import java.util.List;

public interface CartService {



    Cart viewCart( String token);
    boolean addToCart(int idProduct, int qty, String token);

    boolean removeItemCart(int id, String token);

    boolean addCheckoutItem(List<Integer> list);
}
