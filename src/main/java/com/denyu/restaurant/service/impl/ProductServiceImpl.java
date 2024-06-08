package com.denyu.restaurant.service.impl;

import com.denyu.restaurant.dao.model.ProductDao;
import com.denyu.restaurant.dao.service.ProductServiceDao;
import com.denyu.restaurant.model.Product;
import com.denyu.restaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductServiceDao productServiceDao;

    @Override
    public Map getProductAll() {
        List<ProductDao> productsDao = productServiceDao.findAll();
        Map productsList = MappingData(productsDao);
        return productsList;
    }

    public Map MappingData(List<ProductDao> productsDao) {
        Map result = new HashMap();
        for (ProductDao dao : productsDao) {
            Product product = new Product();
            product.setId(String.valueOf(dao.getId()));
            product.setName(dao.getName());
            product.setPhoto_url(dao.getPhoto_url());
            product.setPrice(dao.getPrice());
            product.setStatus(dao.getStatus().equals("Y"));

            if (result.containsKey(dao.getType())) {
                List<Product> productList = (List<Product>) result.get(dao.getType());
                productList.add(product);
                result.put(dao.getType(), productList);
            } else {
                List<Product> productList = new ArrayList<>();
                productList.add(product);
                result.put(dao.getType(), productList);
            }
        }
        return result;
    }

}
