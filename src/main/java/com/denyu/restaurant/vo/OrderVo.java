package com.denyu.restaurant.vo;

import lombok.Data;


@Data
public class OrderVo {
    private String id;
    private String productId;
    private String tableNumber;
    private String userId;

}
