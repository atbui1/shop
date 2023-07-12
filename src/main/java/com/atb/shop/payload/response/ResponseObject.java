package com.atb.shop.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

}
