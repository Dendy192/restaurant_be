package com.denyu.restaurant.controller;

import com.denyu.restaurant.helper.constants.LabelConstant;
import com.denyu.restaurant.helper.utils.ResponseUtils;
import com.denyu.restaurant.service.OrderService;
import com.denyu.restaurant.vo.OrderVo;
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
    public ResponseEntity<?> AddOrder(@RequestBody OrderVo vo) {
        HashMap result = orderService.addOrder(vo);
        if (!checkResult((boolean) result.get(LabelConstant.result))) {
            return ResponseUtils.response(HttpStatus.BAD_REQUEST, result.get(LabelConstant.messages));
        }
        Map msg = new HashMap();
        msg.put(LabelConstant.messages, LabelConstant.orderCreated);
        return ResponseUtils.response(HttpStatus.CREATED, msg);
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public ResponseEntity<?> updateOrder(@RequestBody OrderVo vo) {
        HashMap result = orderService.updateOrder(vo.getId(),vo.getStatus());
        if (!checkResult((boolean) result.get(LabelConstant.result))) {
            return ResponseUtils.response(HttpStatus.BAD_REQUEST, result.get(LabelConstant.messages));
        }
        return ResponseUtils.response(HttpStatus.OK, result.get(LabelConstant.data));
    }

    @RequestMapping(value = "/getOrderToday")
    public ResponseEntity<?> getOrderToday() {
        HashMap result = orderService.getOrderToday();
        if(!checkResult((boolean) result.get(LabelConstant.result))){
            return ResponseUtils.response(HttpStatus.NOT_FOUND, result.get(LabelConstant.messages));
        }
        return ResponseUtils.response(HttpStatus.FOUND, result.get(LabelConstant.data));
    }

    private boolean checkResult(boolean result){
        return result;
    }
}
