package com.atb.shop.payload.request;

import com.atb.shop.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Set<String> strRoles = new HashSet<>();

    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", strRoles=" + strRoles +
                '}';
    }
}
