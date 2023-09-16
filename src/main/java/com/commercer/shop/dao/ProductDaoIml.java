package com.commercer.shop.dao;

import com.commercer.shop.model.Product;
import com.commercer.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductDaoIml implements ProductService {
    @Autowired
    private WebClient webClient;

    @Override
    public List<Product> findAllProduct() {
        return webClient.get().uri("/product/all")
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToFlux(Product.class);
                    }else {
                        return Flux.empty();
                    }
                }).collectList().block();
    }

    @Override
    public List<Product> findProductByCategory(int categoryId ,int page,int limit) {

        return webClient.get().uri(uriBuilder -> uriBuilder.path("/product")
                        .queryParam("category-id", categoryId)
                        .queryParam("page", page)
                        .queryParam("limit", limit).build())
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToFlux(Product.class);
                    }else {
                        return Flux.empty();
                    }
                })
                .collectList()
                .block();
    }

    @Override
    public List<Product> findAllProductByCategory(int categoryId) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/product/category/{id}")

                      .build(categoryId))
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToFlux(Product.class);
                    }else {
                        return Flux.empty();
                    }
                })
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
        return webClient.get().uri("/product/trending-index")
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

    @Override
    public List<Product> findProductSale() {
        return webClient.get().uri("/product/sale")
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToFlux(Product.class);
                    }else {
                        return Flux.empty();
                    }
                }).collectList().block();
    }

    @Override
    public List<Product> findProductByPage(int page,int limit) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/product/shop-trending")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build())
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToFlux(Product.class);
                    }else {
                        return Flux.empty();
                    }
                }).collectList().block();
    }

    @Override
    public List<Product> findSaleProductByPage(int page, int limit) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/product/shop-sale")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build())
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToFlux(Product.class);
                    }else {
                        return Flux.empty();
                    }
                }).collectList().block();
    }

    @Override
    public  List<Product> searchProduct(String keyword) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/search")
                        .queryParam("keyword", keyword)
                        .build())
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()){
                        return clientResponse.bodyToFlux(Product.class);
                    }else {
                        return Flux.empty();
                    }
                }).collectList().block();
    }


}
