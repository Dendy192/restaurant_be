package com.denyu.restaurant.dao.service;


import com.denyu.restaurant.dao.model.ProductDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductServiceDao extends JpaRepository<ProductDao, String> {
    List<ProductDao> findAll(Sort sort);

    ProductDao findProductDaoById(String id);

}
