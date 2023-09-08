package com.commercer.shop.controller;


import com.commercer.shop.model.Product;
import com.commercer.shop.service.ProductService;
import com.commercer.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;


    @GetMapping("")
    public String getProductByCategory(Model model, @RequestParam("category") int id){

        List<Product> products = productService.findProductByCategory(id);
        model.addAttribute("products", products);
        return "public/category";
    }

    @GetMapping("/{id}")
    public String product(Model model, @PathVariable int id){
        Product product = productService.findProductById(id);


        model.addAttribute("product", product);
        model.addAttribute("products", productService.findOtherProduct());
        model.addAttribute("reviews", reviewService.findReviewByProductId(id));
        return "public/product-details";
    }
}
