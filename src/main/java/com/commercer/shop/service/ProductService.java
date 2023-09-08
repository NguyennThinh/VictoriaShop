package com.commercer.shop.service;


import com.commercer.shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findProductByCategory(int categoryId);

    Product findProductById(int id);

    List<Product> findProductTrending();

    List<Product> findProductLatest();
    List<Product>  findOtherProduct();
}
