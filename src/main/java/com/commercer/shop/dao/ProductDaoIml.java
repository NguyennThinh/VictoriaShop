package com.commercer.shop.dao;

import com.commercer.shop.model.Product;
import com.commercer.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductDaoIml implements ProductService {
    @Autowired
    private WebClient webClient;

    @Override
    public List<Product> findProductByCategory(int categoryId) {

        return webClient.get().uri(uriBuilder -> uriBuilder.path("/product")
                .queryParam("category-id", categoryId).build())
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }

    @Override
    public Product findProductById(int id) {

        return webClient.get().uri("/product/"+id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    @Override
    public List<Product> findProductTrending() {
        return webClient.get().uri("/product/product-trending")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }

    @Override
    public List<Product> findProductLatest() {
        return webClient.get().uri("/product/latest")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }

    @Override
    public List<Product> findOtherProduct() {
        return webClient.get().uri("/product/other")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }
}
