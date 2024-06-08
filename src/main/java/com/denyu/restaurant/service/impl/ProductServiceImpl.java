package com.denyu.restaurant.service.impl;

import com.denyu.restaurant.dao.model.ProductDao;
import com.denyu.restaurant.dao.service.ProductServiceDao;
import com.denyu.restaurant.vo.ProductVo;
import com.denyu.restaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            ProductVo productVo = new ProductVo();
            productVo.setId(String.valueOf(dao.getId()));
            productVo.setName(dao.getName());
            productVo.setPhoto_url(dao.getPhoto_url());
            productVo.setPrice(dao.getPrice());
            productVo.setStatus(dao.getStatus().equals("Y"));

            if (result.containsKey(dao.getType())) {
                List<ProductVo> productVoList = (List<ProductVo>) result.get(dao.getType());
                productVoList.add(productVo);
                result.put(dao.getType(), productVoList);
            } else {
                List<ProductVo> productVoList = new ArrayList<>();
                productVoList.add(productVo);
                result.put(dao.getType(), productVoList);
            }
        }
        return result;
    }

}
