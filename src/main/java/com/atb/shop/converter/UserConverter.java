package com.atb.shop.converter;

import com.atb.shop.entity.User;
import com.atb.shop.payload.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toEntity(UserRequest dto) {
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRoles(dto.getRoles());
        return entity;
    }

}
