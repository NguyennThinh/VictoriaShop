package com.commercer.shop.service;

import com.commercer.shop.model.Review;
import com.commercer.shop.model.User;

public interface UserService {


    User myProfile(String token);

    Review postReview(String comment,int productId, int rating, String token);
}
