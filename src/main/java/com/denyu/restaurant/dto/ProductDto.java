package com.denyu.restaurant.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String name;
    private int qty;
    private String url;
    private String price;

}
