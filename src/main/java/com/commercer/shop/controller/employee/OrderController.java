package com.commercer.shop.controller.employee;

import com.commercer.shop.interceptor.ServiceInterceptor;
import com.commercer.shop.model.Order;
import com.commercer.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String myOrder(Model model) {
        if (ServiceInterceptor.TOKEN == null){
            return "redirect:/";
        }
        List<Order> orders = orderService.getMyOrder(ServiceInterceptor.TOKEN);
        model.addAttribute("orders", orders);

        return "public/order-confirmation";
    }
}
