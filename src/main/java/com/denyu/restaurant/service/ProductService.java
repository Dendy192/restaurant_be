package com.denyu.restaurant.service;

import com.denyu.restaurant.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductService {

    List<Product> getProductAll();
}
