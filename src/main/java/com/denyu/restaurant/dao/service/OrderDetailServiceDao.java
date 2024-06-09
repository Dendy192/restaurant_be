package com.denyu.restaurant.dao.service;

import com.denyu.restaurant.dao.model.OrderDao;
import com.denyu.restaurant.dao.model.OrderDetailDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailServiceDao extends JpaRepository<OrderDetailDao, String> {
    List<OrderDetailDao> findOrderDetailDaoByOrder(OrderDao dao);
}
