package com.commercer.shop.controller.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {


    @GetMapping("/order")
    public String myOrder(){


        return "public/checkout_page";
    }
}
