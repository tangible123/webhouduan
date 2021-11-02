package com.web.demo.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.web.demo.model.User;
// 实现与数据库语句的接口

@Repository
@Mapper
public interface UserMapper {

    Integer CheckLogin(String userNum,String phone,String password);

    Integer GetUserNumberByMaxId();

    Integer CreateUserByUser(User user);

    Integer UpdatePasswordByPhone(String phone,String password);

    User selectByName(String userName);

    Integer registerUser(String userNum,String username,String phone,String email,String password);


    Integer UpdatePasswordByEmail(String email,String password);

    Integer checkUserNum(String userNum);

    Integer checkUserName(String username);

    Integer checkPhone(String phone);

    Integer checkEmail(String email);





}
