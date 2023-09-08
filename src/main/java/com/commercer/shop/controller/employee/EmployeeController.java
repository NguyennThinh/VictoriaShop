package com.commercer.shop.controller.employee;

import com.commercer.shop.interceptor.ServiceInterceptor;
import com.commercer.shop.model.User;
import com.commercer.shop.model.dto.Account;
import com.commercer.shop.service.AuthService;
import com.commercer.shop.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmployeeController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@ModelAttribute("account") Account account, HttpServletResponse response) {

        User user = authService.register(account);

        if (user != null)
            loginWithRegister(account, response);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("account") Account account, HttpServletResponse response) {

        String token = authService.Login(account.getEmail(), account.getPassword());
        if (token != null) {
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(36000);
            response.addCookie(cookie);
        }
        return "redirect:/";
    }

    //Login when register success
    public void loginWithRegister(Account account, HttpServletResponse response) {

        String token = authService.Login(account.getEmail(), account.getPassword());
        if (token != null) {
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(36000);
            response.addCookie(cookie);
        }
    }


    @GetMapping("/profile")
    public String profile( Model model) {
        if (ServiceInterceptor.TOKEN == null) {
            return "redirect:/";
        }
        User user = userService.myProfile( ServiceInterceptor.TOKEN);
        if (user !=null) {
            model.addAttribute("user", user);
            return "public/my-account";
        }else {
            return "redirect:/";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        boolean isLogout = authService.logout();

        if (isLogout) {
            Cookie cookie = new Cookie("token", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/";
    }


    @PostMapping("/emp/comment")
    public RedirectView reviewProduct(@RequestParam("url") String url,
                                      @RequestParam("review") String comment,
                                      @RequestParam("rating") int rating,
                                      @RequestParam("product") int idProduct) {

        userService.postReview(comment, idProduct, rating, ServiceInterceptor.TOKEN);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);

        return redirectView;
    }

    @PostMapping("/emp/checkout")
    public String checkout() {

        return null;
    }
}
