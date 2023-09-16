package com.commercer.shop.controller.employee;

import com.commercer.shop.interceptor.ServiceInterceptor;
import com.commercer.shop.model.Cart;
import com.commercer.shop.model.Product;
import com.commercer.shop.model.User;
import com.commercer.shop.service.CartService;
import com.commercer.shop.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/views")
    public String viewMyCart( Model model){

        Cart  cart = cartService.viewCart(ServiceInterceptor.TOKEN);
        model.addAttribute("cart", cart);
        return "public/cart_page";
    }

     @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("pid") int id, @RequestParam("qty") int qty) {
         cartService.addToCart(id, qty, ServiceInterceptor.TOKEN);

        return "redirect:/";
    }

    @PostMapping("/remove-item")
    public RedirectView removeItem(@RequestParam("item-id") int id, @RequestParam("url") String url){


        boolean result =  cartService.removeItemCart(id, ServiceInterceptor.TOKEN);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }


    @GetMapping("/checkout-page")
    public String checkout(Model model){

        User user = userService.myProfile( ServiceInterceptor.TOKEN);
        Cart  cart = cartService.viewCart(ServiceInterceptor.TOKEN);

        model.addAttribute("user", user);
        model.addAttribute("cart", cart);

        return "public/checkout_page";

    }

    @PostMapping("/checkout")
    public String checkousdft(@RequestBody Map<String , String> body){

        System.out.println(body);


        return "public/checkout_page";

    }


}
