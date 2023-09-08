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
public class Color {

    private int id;

    private String codeColor;
    private String nameColor;
    private List<ProductImage> productImages;
}
