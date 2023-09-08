package com.commercer.shop.dao;

import com.commercer.shop.model.Category;
import com.commercer.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CategoryDaoImpl implements CategoryService {
    private  final String URL_MAPPING = "/category";
    @Autowired
    private WebClient webClient;
    @Override
    public List<Category> findAll() {

        return webClient.get().uri(URL_MAPPING+"/findAll")
                .retrieve().bodyToFlux(Category.class).collectList().block();
    }
}
