package com.denyu.restaurant.dao.service;

import com.denyu.restaurant.dao.model.OrderDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderServiceDao extends JpaRepository<OrderDao, String> {
}
