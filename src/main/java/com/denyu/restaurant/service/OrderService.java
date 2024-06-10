package com.denyu.restaurant.service;

import com.denyu.restaurant.vo.OrderVo;

import java.util.HashMap;

public interface OrderService {
    HashMap addOrder(OrderVo lVo);

    HashMap getOrderToday();

    HashMap updateOrder(String id,String status);

}
