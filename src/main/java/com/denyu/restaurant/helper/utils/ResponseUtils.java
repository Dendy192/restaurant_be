package com.denyu.restaurant.helper.utils;

import com.denyu.restaurant.helper.constants.LabelConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static Map ResponseError(String messages){
        Map response = new HashMap();
        response.put(LabelConstant.messages, messages);
        return response;
    }
    public static <T> ResponseEntity<?> response(HttpStatus httpStatus, T body){
        Map responseBody = new HashMap<>();
        Map responseMeta = new HashMap<>();
        responseMeta.put(LabelConstant.code, httpStatus.value());
        responseBody.put(LabelConstant.meta, responseMeta);
        responseBody.put(LabelConstant.data, body);
        if(httpStatus.value() >= 400) {
            responseBody.put(LabelConstant.data,ResponseError((String) body));
        }
        return ResponseEntity.status(httpStatus).body(responseBody);
    }
}
