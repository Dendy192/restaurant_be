package com.denyu.restaurant.vo;

import lombok.Data;

@Data
public class ProductVo {
    private String id;
    private String name;
    private String photo_url;
    private String price;
    private boolean status;


}
