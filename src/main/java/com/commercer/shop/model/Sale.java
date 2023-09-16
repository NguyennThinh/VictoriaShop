package com.commercer.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sale {

    private int id;

    private String saleName;

    private int saleValue;

    private Date startDate;

    private Date endDate;

    private List<Product> productsSale;
}
