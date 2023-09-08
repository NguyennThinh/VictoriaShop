package com.commercer.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    private int pid;

    private String code;

    private String productName;

    private double price;

    private int quantity;

    private  String description;
    private List<ProductImage> productImage;

    private int status;

    private int quantitySold;


    @JsonIgnore
    private Category category;
    @JsonIgnore
    private Sale sale;

    private List<Color> colors;

    private List<Review> reviews;
}
