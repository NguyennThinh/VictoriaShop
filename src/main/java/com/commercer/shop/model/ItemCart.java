package com.commercer.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemCart {

    private long id;

    private Cart cart;

    private Product product;

    private int quantity;
}
