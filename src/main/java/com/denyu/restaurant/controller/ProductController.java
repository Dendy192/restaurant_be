package com.denyu.restaurant.controller;

import com.denyu.restaurant.helper.constants.LabelConstant;
import com.denyu.restaurant.service.ProductService;
import com.denyu.restaurant.helper.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/all")
    public ResponseEntity<?> productGetall(){
        Map products = productService.getProductAll();
        if(products.isEmpty()){
            return ResponseUtils.response(HttpStatus.NOT_FOUND, LabelConstant.productNotFound);
        }
        return ResponseUtils.response(HttpStatus.OK, products);
    }
}
