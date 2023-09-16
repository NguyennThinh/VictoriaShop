package com.commercer.shop.service;


import com.commercer.shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();

    List<Product> findProductByCategory(int categoryId,int page,int limit);
    List<Product> findAllProductByCategory(int categoryId);

    Product findProductById(int id);

    List<Product> findProductTrending();

    List<Product> findProductLatest();
    List<Product>  findOtherProduct();

    List<Product>  findProductSale();

    List<Product>  findProductByPage(int page,int limit);
    List<Product>  findSaleProductByPage(int page,int limit);

    List<Product> searchProduct(String keyword);
}
