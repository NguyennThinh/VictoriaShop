package com.commercer.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Review {

    private int id;

    private String comment;

    private  int  rating;

    private User user;

    private Product product;
}
