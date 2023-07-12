package com.atb.shop.controller;


import com.atb.shop.commons.ResponseFactory;
import com.atb.shop.commons.ResponseSuccess;
import com.atb.shop.payload.request.UserRequest;
import com.atb.shop.payload.response.ResponseObject;
import com.atb.shop.service.serviceimp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/auth/v1")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;


    @GetMapping("/user")
    public ResponseEntity<?> showAllUser() {
//        if (userServiceImp.showAllUser().size() > 0) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "found users", userServiceImp.showAllUser())
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "not found users", "")
//        );
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "found users", userServiceImp.showAllUser())
        );
    }
    @GetMapping("/userN")
    public ResponseEntity<?> showAllUserN() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseSuccess(
                        ResponseSuccess.SUCCESS,
                        "found users",
                        userServiceImp.showAllUser()
                )
        );
    }

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
//        System.out.println("UserController 111111111111111111111111111111111111111");
//        userServiceImp.registerUser(userRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(
//               new ResponseObject("ok", "register user success", userRequest)
//        );

        userServiceImp.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseSuccess(
                        ResponseSuccess.SUCCESS,
                        "found users",
                        userRequest
                )
        );
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        userServiceImp.login(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseSuccess(
                        ResponseSuccess.SUCCESS,
                        "login success",
                        userServiceImp.login(userRequest)
                )
        );
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        userServiceImp.logout();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseSuccess(
                        ResponseSuccess.SUCCESS,
                        "logout success",
                        ""
                )
        );
    }

}
