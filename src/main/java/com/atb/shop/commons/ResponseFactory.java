package com.atb.shop.commons;

public class ResponseFactory {
    public static ResponseSuccess success(Object data) {
        return new ResponseSuccess("ok", "", data);
    }

    public static ResponseSuccess success(String message, Object data) {
        return new ResponseSuccess("ok", message, data);
    }

    public static ResponseSuccess failure(String message) {
        return new ResponseSuccess("not ok", message, "");
    }

    public static ResponseSuccess failure(String message, Object data) {
        return new ResponseSuccess("not ok", message, data);
    }
}
