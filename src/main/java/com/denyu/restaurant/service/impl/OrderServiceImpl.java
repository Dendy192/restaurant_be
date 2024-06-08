package com.denyu.restaurant.service.impl;

import com.denyu.restaurant.dao.model.OrderDao;
import com.denyu.restaurant.dao.model.OrderDetailDao;
import com.denyu.restaurant.dao.model.ProductDao;
import com.denyu.restaurant.dao.model.UserDao;
import com.denyu.restaurant.dao.service.OrderDetailServiceDao;
import com.denyu.restaurant.dao.service.OrderServiceDao;
import com.denyu.restaurant.dao.service.ProductServiceDao;
import com.denyu.restaurant.dao.service.UserServiceDao;
import com.denyu.restaurant.helper.constants.LabelConstant;
import com.denyu.restaurant.helper.utils.DateUtils;
import com.denyu.restaurant.service.OrderService;
import com.denyu.restaurant.vo.ListOrderVo;
import com.denyu.restaurant.vo.OrderVo;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserServiceDao userServiceDao;

    @Autowired
    ProductServiceDao productServiceDao;

    @Autowired
    OrderServiceDao orderServiceDao;

    @Autowired
    OrderDetailServiceDao orderDetailServiceDao;

    @Transactional
    public HashMap addOrder(ListOrderVo lVo) {
        HashMap result = new HashMap();
        try {
            Optional<UserDao> OuserDao = userServiceDao.findUserDaoByName(lVo.getName());
            UserDao userDao = OuserDao.get();
            List<OrderVo> lOrderVo = lVo.getData();
            List<OrderDetailDao> lOrderDao = new ArrayList<>();
            OrderDao orderDao = new OrderDao();

            orderDao.setUserDao(userDao);
            orderDao.setTable(Integer.parseInt(lVo.getTableNumber()));
            orderDao.setStatus("Process");
            orderDao.setDateTime(DateUtils.getTimeSql());
            orderServiceDao.save(orderDao);
            for (OrderVo vo : lOrderVo) {
                ProductDao productDao = productServiceDao.findProductDaoById(vo.getProductId());
                OrderDetailDao orderDetailDao = new OrderDetailDao();
                orderDetailDao.setProduct(productDao);
                orderDetailDao.setOrder(orderDao);
                orderDetailServiceDao.save(orderDetailDao);
                lOrderDao.add(orderDetailDao);
            }


            result.put(LabelConstant.result, true);
            result.put(LabelConstant.data, orderDao);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(LabelConstant.result, false);
            result.put(LabelConstant.messages, e.getMessage());
        }
        return result;
    }
}
