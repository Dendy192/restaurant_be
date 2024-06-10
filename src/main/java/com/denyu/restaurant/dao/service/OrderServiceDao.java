package com.denyu.restaurant.dao.service;

import com.denyu.restaurant.dao.model.OrderDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderServiceDao extends JpaRepository<OrderDao, String> {
    List<OrderDao> findOrderDaoByDateTimeBetween(Timestamp startDateTime, Timestamp endDateTime, Sort sort);
    OrderDao findOrderDaoById(String id);
}
