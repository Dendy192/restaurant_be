package com.denyu.restaurant.vo;

import lombok.Data;

import java.util.List;

@Data
public class ListOrderVo {
    private String name;
    private String tableNumber;
    private List<OrderVo> data;
}
