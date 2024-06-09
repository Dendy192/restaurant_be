package com.denyu.restaurant.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String id;
    private int tableNumber;
    private UserDto user;
}
