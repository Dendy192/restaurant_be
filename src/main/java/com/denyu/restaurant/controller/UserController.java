package com.denyu.restaurant.controller;

import com.denyu.restaurant.helper.constants.LabelConstant;
import com.denyu.restaurant.helper.utils.ResponseUtils;
import com.denyu.restaurant.service.UserService;
import com.denyu.restaurant.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

//    @RequestMapping(value = "/findUser")
//    public ResponseEntity<?> GetUserById(@RequestBody UserVo vo){
//
//        return ResponseUtils.response(HttpStatus.OK, products);
//    }
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    public ResponseEntity<?> GetUserById(@RequestBody UserVo vo){
        Map response = userService.adduser(vo);
        boolean result = (boolean) response.get("result");
        if(!result){
            if(response.get(LabelConstant.messages) !=null){
                return ResponseUtils.response(HttpStatus.FOUND, response.get(LabelConstant.messages));
            }
        }
        return ResponseUtils.response(HttpStatus.CREATED, response.get(LabelConstant.data));
    }
}
