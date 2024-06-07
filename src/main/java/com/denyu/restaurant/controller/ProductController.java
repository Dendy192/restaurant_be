package com.denyu.restaurant.controller;

import com.denyu.restaurant.constant.ProductConstant;
import com.denyu.restaurant.model.Product;
import com.denyu.restaurant.service.ProductService;
import com.denyu.restaurant.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/product")
    public ResponseEntity<?> productGetall(){
        List<Product> products = productService.getProductAll();
        if(products.isEmpty()){
            return ResponseUtils.response(HttpStatus.NOT_FOUND, ProductConstant.productNotFound);
        }
        return ResponseUtils.response(HttpStatus.OK, products);
    }
}
