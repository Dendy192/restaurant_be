package com.denyu.restaurant.service.impl;

import com.denyu.restaurant.dao.model.UserDao;
import com.denyu.restaurant.dao.service.UserServiceDao;
import com.denyu.restaurant.helper.constants.LabelConstant;
import com.denyu.restaurant.service.UserService;
import com.denyu.restaurant.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserServiceDao userServiceDao;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Transactional
    public Map adduser(UserVo vo) {
        Map result = new HashMap();
        try {
            Optional<UserDao> ouser = userServiceDao.findUserDaoByName(vo.getName());
            if (ouser.isEmpty()) {
                UserDao user = new UserDao();
                user.setId(generateIdUsingName(vo.getName()).toString());
                user.setName(vo.getName());
                user.setPassword(vo.getPassword());
                user.setRole(vo.getRole());
                userServiceDao.save(user);
                result.put(LabelConstant.result, true);
                result.put(LabelConstant.data, user);
            }else{
                result.put(LabelConstant.result, false);
                result.put(LabelConstant.data, maskingData(ouser.get()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put(LabelConstant.result, false);
            result.put(LabelConstant.messages, e.getMessage());
        }
        return result;
    }

    public UUID generateIdUsingName(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return UUID.nameUUIDFromBytes(bytes);
    }

    public Map maskingData(UserDao dao){
        Map result = new HashMap();
        result.put(LabelConstant.messages, LabelConstant.userFound);
        result.put(LabelConstant.id, dao.getId());
        result.put(LabelConstant.name, dao.getName());
        result.put(LabelConstant.role, dao.getRole());
        return result;
    }

//    public boolean checkPassword(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
}
