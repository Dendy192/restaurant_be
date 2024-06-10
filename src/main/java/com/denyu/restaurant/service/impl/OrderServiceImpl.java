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
import com.denyu.restaurant.vo.OrderDetailVo;
import com.denyu.restaurant.vo.OrderVo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public HashMap addOrder(OrderVo vo) {
        HashMap result = new HashMap();
        try {
            Optional<UserDao> OuserDao = userServiceDao.findUserDaoByName(vo.getName());
            UserDao userDao = OuserDao.get();
            List<OrderDetailVo> lOrderVo = vo.getData();
            List<OrderDetailDao> lOrderDao = new ArrayList<>();
            OrderDao orderDao = new OrderDao();

            orderDao.setUserDao(userDao);
            orderDao.setTable(Integer.parseInt(vo.getTableNumber()));
            orderDao.setStatus(LabelConstant.process);
            orderDao.setDateTime(DateUtils.getTimeSql());
            orderServiceDao.save(orderDao);
            for (OrderDetailVo odVo : lOrderVo) {
                ProductDao productDao = productServiceDao.findProductDaoById(odVo.getProductId());
                OrderDetailDao orderDetailDao = new OrderDetailDao();
                orderDetailDao.setProduct(productDao);
                orderDetailDao.setOrder(orderDao);
                orderDetailDao.setQty(Integer.parseInt(odVo.getQty()));
                orderDetailDao.setNotes(odVo.getNotes());
                orderDetailServiceDao.save(orderDetailDao);
                lOrderDao.add(orderDetailDao);
            }


            result.put(LabelConstant.result, true);
            result.put(LabelConstant.data, vo);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(LabelConstant.result, false);
            result.put(LabelConstant.messages, e.getMessage());
        }
        return result;
    }

    @Override
    public HashMap getOrderToday() {
        Sort sort = Sort.by(Sort.Direction.DESC, "status");
        List<OrderDao> orderDaoList = orderServiceDao.findOrderDaoByDateTimeBetween(DateUtils.toStartOfDay(DateUtils.getTimeSql()), DateUtils.toEndOfDay(DateUtils.getTimeSql()), sort);
        List<OrderVo> lVo = new ArrayList<>();

        for (OrderDao dao : orderDaoList) {
            List<OrderDetailDao> odd = orderDetailServiceDao.findOrderDetailDaoByOrder(dao);
            OrderVo vo = mappingVo(dao, odd);
            lVo.add(vo);
        }
        HashMap result = new HashMap();
        if (orderDaoList.isEmpty()) {
            result.put(LabelConstant.result, false);
            result.put(LabelConstant.messages, LabelConstant.orderNotFound);
        } else {
            result.put(LabelConstant.result, true);
            result.put(LabelConstant.data, lVo);
        }
        return result;
    }

    @Transactional
    public HashMap updateOrder(String id, String status) {
        HashMap result = new HashMap();
        try{
            OrderDao oDao = orderServiceDao.findOrderDaoById(id);
            oDao.setStatus(LabelConstant.payment);
            orderServiceDao.save(oDao);
            result.put(LabelConstant.result, true);
            HashMap mappingMessage = new HashMap<>();
            mappingMessage.put(LabelConstant.id, id);
            mappingMessage.put(LabelConstant.status, status);
            mappingMessage.put(LabelConstant.tableNumber, oDao.getTable());
            result.put(LabelConstant.data, mappingMessage);
        }catch (Exception e){
            e.printStackTrace();
            result.put(LabelConstant.result, false);
            result.put(LabelConstant.messages, e.getMessage());
        }
        return result;
    }

    public OrderVo mappingVo(OrderDao dao, List<OrderDetailDao> odd) {
        OrderVo vo = new OrderVo();
        List<OrderDetailVo> orderDetailVoList = new ArrayList<>();
        for (OrderDetailDao od : odd) {
            OrderDetailVo odVo = new OrderDetailVo();
            odVo.setProductId(od.getProduct().getId());
            odVo.setQty(String.valueOf(od.getQty()));
            odVo.setPrice(od.getProduct().getPrice());
            odVo.setNotes(od.getNotes());
            orderDetailVoList.add(odVo);
        }
        vo.setStatus(dao.getStatus());
        vo.setData(orderDetailVoList);
        vo.setTableNumber(String.valueOf(dao.getTable()));
        vo.setId(dao.getId());
        vo.setName(dao.getUserDao().getName());
        return vo;
    }
}
