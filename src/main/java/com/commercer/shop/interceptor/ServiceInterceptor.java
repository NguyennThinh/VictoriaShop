package com.commercer.shop.interceptor;

import com.commercer.shop.model.Cart;
import com.commercer.shop.model.Category;
import com.commercer.shop.model.User;
import com.commercer.shop.service.CartService;
import com.commercer.shop.service.CategoryService;
import com.commercer.shop.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServiceInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(ServiceInterceptor.class);
    public static String TOKEN = "";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //   System.out.println("Pre Handle method is Calling");
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);

        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> cookieMap = new HashMap<>();


        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }

            Cookie cookieToken = cookieMap.get("token");
            if (cookieToken != null) {
                String token = cookieToken.getValue();

                TOKEN = token;
                User user = userService.myProfile(token);
                if (user != null) {
                    Cart cart = cartService.viewCart(token);
                    Map<String, String> authorization = new HashMap<>();
                    authorization.put("userName", user.getLastName());
                    request.setAttribute("authorization", authorization);
                    request.setAttribute("cart", cart);
                }
            }
    }
        return true;
}

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        //  System.out.println("Request and Response is completed");

    }
}
