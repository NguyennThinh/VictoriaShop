package com.commercer.shop.model;

import com.commercer.shop.model.dto.RandomCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {


    private String uid;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isDelete;

    private Cart cart;

    private Role role;

    private List<Order> orders;

    private List<Review> reviews;


}
