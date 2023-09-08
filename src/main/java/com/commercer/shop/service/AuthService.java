package com.commercer.shop.service;

import com.commercer.shop.model.User;
import com.commercer.shop.model.dto.Account;

public interface AuthService {

    User register(Account account);

    String Login(String email , String password);

    boolean logout();
}
