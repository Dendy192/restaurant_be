package com.denyu.restaurant.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static <T> ResponseEntity<?> response(HttpStatus httpStatus, T body){
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("value", httpStatus.value());
        responseBody.put("status", httpStatus.name());
        responseBody.put("data", body);
        return ResponseEntity.status(httpStatus).body(responseBody);
    }
}
