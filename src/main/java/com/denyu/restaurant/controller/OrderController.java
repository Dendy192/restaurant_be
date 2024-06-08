package com.denyu.restaurant.controller;

import com.denyu.restaurant.helper.constants.LabelConstant;
import com.denyu.restaurant.helper.utils.ResponseUtils;
import com.denyu.restaurant.service.OrderService;
import com.denyu.restaurant.vo.ListOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ResponseEntity<?> AddOrder(@RequestBody ListOrderVo lVo) {
        HashMap result = orderService.addOrder(lVo);
        boolean rs = (boolean) result.get(LabelConstant.result);
        if (!rs) {
            return ResponseUtils.response(HttpStatus.BAD_REQUEST, result.get(LabelConstant.messages));
        }
        Map msg = new HashMap();
        msg.put(LabelConstant.messages, LabelConstant.orderCreated);
        return ResponseUtils.response(HttpStatus.CREATED, msg);
    }
}
