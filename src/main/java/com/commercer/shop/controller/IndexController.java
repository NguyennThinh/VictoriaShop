package com.commercer.shop.controller;


import com.commercer.shop.service.CategoryService;
import com.commercer.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/index"})
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();

        view.addObject("productTrending", productService.findProductTrending());
        view.addObject("productLatest", productService.findProductLatest());

        view.setViewName("public/index3");

        return view;
    }
}
