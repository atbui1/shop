package com.atb.shop.service.serviceimp;

import com.atb.shop.commons.ResponseFactory;
import com.atb.shop.converter.UserConverter;
import com.atb.shop.entity.ERole;
import com.atb.shop.entity.Role;
import com.atb.shop.entity.User;
import com.atb.shop.exception.UserException;
import com.atb.shop.payload.request.UserRequest;
import com.atb.shop.repository.RoleRepository;
import com.atb.shop.repository.UserRepository;
import com.atb.shop.security.jwt.JwtUtils;
import com.atb.shop.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserConverter userConverter;


    @Override
    public List<User> showAllUser() {
        if (userRepository.findAll().size() > 0) {
            return userRepository.findAll();
        }

        throw UserException.UsernameAlreadyExistException();
    }

    @Override
    public User login(UserRequest userRequest) {
        if (userRequest.getUsername().trim().equals("")) {
            throw UserException.isEmpty("username is null");
        } else if (userRequest.getPassword().trim().equals("")) {
            throw UserException.isEmpty("password is null");
        } else {
            Optional<User> user = userRepository.findByUsername(userRequest.getUsername());
            if (!user.isPresent()) {
                throw UserException.isEmpty("username is wrong");
            } else {
                if (user.get().getPassword().trim().equals(userRequest.getPassword().trim())) {
//                    throw UserException.isEmpty("login success");
//                    User entity = userConverter.toEntity(userRequest);
                    return user.get();
                } else {
                    throw UserException.isEmpty("password is wrong");
                }
            }
        }
    }

    @Override
    public void logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    }

    @Override
    public void registerUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw UserException.UsernameAlreadyExistException();
        } else if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw UserException.EmailAlreadyExistException();
        }

        Set<String> strRoles = userRequest.getStrRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles.size() < 1) {
            Optional<Role> role = roleRepository.findByName(ERole.ROLE_USER);
            roles.add(role.get());

        } else {
            strRoles.forEach((role)->{
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found.ROLE_ADMIN --> AuthController"));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found.ROLE_ADMIN --> AuthController"));
                        roles.add(modRole);
                        break;
//                    case "user":
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found.ROLE_ADMIN --> AuthController"));
//                        roles.add(userRole);
//                        break;
                    default:
                        Role defaultRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found.ROLE_ADMIN --> AuthController"));
                        roles.add(defaultRole);
                }
            });
        }

        System.out.println("strRoles: " + strRoles);
        System.out.println("roles: " + roles);
        userRequest.setRoles(roles);
        User user = userConverter.toEntity(userRequest);

        System.out.println("roles dto: " + userRequest.getRoles());
        System.out.println(userRequest);
        System.out.println(user);

        userRepository.save(user);

    }
}
