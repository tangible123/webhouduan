package com.web.demo.service;

import com.web.demo.dao.UserMapper;
import com.web.demo.model.User;
import com.web.demo.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 利用数据库查询语句的接口实现逻辑判断，返回结果
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper; // 调用mapper接口 去获取 数据库相关的资源

    @Override
    public Integer CheckLogin(String userNum,String password) {

        String phone = userNum;
        Integer idx = -1;
        idx = userMapper.CheckLogin(userNum,phone,password);
        if(idx>=0) return idx;
        return -1;
    }

    @Override
    public  Integer GetUserNumber() {
        return userMapper.GetUserNumberByMaxId();
    }

    @Override
    public Integer CreateUser(User user) {
        return userMapper.CreateUserByUser(user);
    }

    @Override
    public  Integer UpdatePassword(String phone,String password) {
        return userMapper.UpdatePasswordByPhone(phone,password);
    }

    @Override
    public Integer  register(String userNum,String username,String phone,String email,String password) {

         return  userMapper.registerUser(userNum,username,phone,email,password);

    }

    @Override
    public  Integer modifyPasswordByPhone(String phone,String password) {
        return userMapper.UpdatePasswordByPhone(phone,password);
    }
    @Override
    public  Integer modifyPasswordByEmail(String email,String password) {
        return userMapper.UpdatePasswordByEmail(email,password);
    }

    @Override
    public Integer checkUserNum(String userNum) {
        return userMapper.checkUserNum(userNum);
    }
    @Override
    public Integer checkUserName(String username) {
        return userMapper.checkUserName(username);
    }
    @Override
    public Integer checkEmail(String email) {
        return userMapper.checkEmail(email);
    }
    @Override
    public Integer checkPhone(String phone) {
        return userMapper.checkPhone(phone);
    }
}
