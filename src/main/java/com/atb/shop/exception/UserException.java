package com.atb.shop.exception;

import org.springframework.security.core.AuthenticationException;

public class UserException extends AuthenticationException {

    public UserException(String msg) {
        super(msg);
    }

    public static UserException UsernameAlreadyExistException() {
        return new UserException("UserException: Username already exist");
    }
    public static UserException EmailAlreadyExistException() {
        return new UserException("UserException: Email already exist");
    }

    public static UserException isEmpty(String msg) {
        return new UserException(msg);
    }
}
