package com.atb.shop.commons;

public record ResponseSuccess(String status,
                              String message,
                              Object data) {
    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";
}
