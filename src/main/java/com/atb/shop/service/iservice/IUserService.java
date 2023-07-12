package com.atb.shop.service.iservice;

import com.atb.shop.entity.User;
import com.atb.shop.payload.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> showAllUser();

    User login(UserRequest userRequest);
    void logout();
    void registerUser(UserRequest userRequest);


}
