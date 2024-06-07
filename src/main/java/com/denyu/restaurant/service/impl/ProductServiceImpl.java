package com.denyu.restaurant.service.impl;

import com.denyu.restaurant.dao.model.ProductDao;
import com.denyu.restaurant.dao.service.ProductServiceDao;
import com.denyu.restaurant.model.Product;
import com.denyu.restaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductServiceDao productServiceDao;

    @Override
    public List<Product> getProductAll() {
        List<ProductDao> productsDao = productServiceDao.findAll();
        List<Product> productsList = new ArrayList<>();
        for(ProductDao dao : productsDao){
            Product product = new Product();
            product.setId(String.valueOf(dao.getId()));
            product.setName(dao.getName());
            product.setPhoto_url(dao.getPhoto_url());
            product.setPrice(dao.getPrice());
//            boolean status = dao.getStatus().equals("Y");
            product.setStatus(dao.getStatus().equals("Y"));
            productsList.add(product);
        }
        return productsList;
    }
}
