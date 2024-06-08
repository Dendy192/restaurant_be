package com.denyu.restaurant.service;

import com.denyu.restaurant.vo.ListOrderVo;

import java.util.HashMap;

public interface OrderService {
    HashMap addOrder(ListOrderVo lVo);
}
