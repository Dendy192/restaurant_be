package com.denyu.restaurant.vo;

import lombok.Data;

import java.util.List;


@Data
public class OrderVo {
    private String id;
    private String tableNumber;
    private String name;
    private String status;
    private List<OrderDetailVo> data;

}
