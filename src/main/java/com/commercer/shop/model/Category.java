package com.commercer.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    private int cid;

    private String code;

    private String categoryName;

    private List<Product> products;
}
