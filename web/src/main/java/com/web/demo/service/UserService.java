package com.web.demo.service;

//  利用数据库查询后的数据 进行判断 的接口
import com.web.demo.model.User;
import com.web.demo.vo.RegisterObject;

public interface UserService {

     Integer CheckLogin(String number,String password);

     Integer GetUserNumber();

     Integer CreateUser(User user);

     Integer UpdatePassword(String phone,String password);

     //用户注册
     Integer register(String userNum,String username,String phone,String email,String password);

     Integer modifyPasswordByPhone(String phone,String password);

     Integer modifyPasswordByEmail(String email,String password);

     Integer checkUserNum(String userNum);

     Integer checkUserName(String username);

     Integer checkPhone(String phone);

     Integer checkEmail(String email);



}
