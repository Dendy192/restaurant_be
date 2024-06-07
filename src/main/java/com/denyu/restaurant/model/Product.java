package com.denyu.restaurant.model;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private String photo_url;
    private String price;
    private boolean status;


}
