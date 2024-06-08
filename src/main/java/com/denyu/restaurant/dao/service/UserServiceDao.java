package com.denyu.restaurant.dao.service;

import com.denyu.restaurant.dao.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserServiceDao extends JpaRepository<UserDao, String> {
//    Optional<UserDao> findUserById(String id);
    Optional<UserDao> findUserDaoByName(String name);
}
