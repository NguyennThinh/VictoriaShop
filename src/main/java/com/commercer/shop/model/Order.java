package com.commercer.shop.model;

import com.commercer.shop.model.dto.RandomCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    private int oid;
    private String code = RandomCode.getRandomString(12);

    private List<OrderItem> items;
    private User user;

    private int status;
}
