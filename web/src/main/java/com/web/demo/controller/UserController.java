package com.web.demo.controller;


import com.web.demo.common.ApiRestResponse;
import com.web.demo.util.JwtUtil;
import com.web.demo.util.MD5Utils;
import com.web.demo.vo.LoginObject;
import com.web.demo.exception.ExceptionEnum;
import com.web.demo.service.UserService;
import com.web.demo.vo.RegisterObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/index")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestBody(required = false) RegisterObject registerObject) throws Exception {

        Integer success = 0;
         registerObject.setPassword(MD5Utils.getMD5Str(registerObject.getPassword()));
       success = userService.register(registerObject.getUserNum(),registerObject.getUsername()
        ,registerObject.getPhone(),registerObject.getEmail(),registerObject.getPassword());
        if(success!=0)
        //5.写入成功后返回成功
        return ApiRestResponse.success();
        else return  ApiRestResponse.error(ExceptionEnum.SYSTEM_ERROR);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ApiRestResponse UserLogin(@RequestBody(required = false) LoginObject loginObject, HttpServletResponse response)  {

        JSONObject json = new JSONObject();
         Integer temp = userService.CheckLogin(loginObject.getUserNum(),loginObject.getPassword());

         if(temp>=1)
         {
             String jwt = JwtUtil.createJWT(loginObject.getUserNum()); // 由学号创建jwt
             json.put("token",jwt);
             response.setHeader("Authorization", jwt);
             response.setHeader("Access-control-Expose-Headers", "Authorization");

             System.out.println("能登录" + temp);

             return  ApiRestResponse.success();


         }

         else return  ApiRestResponse.error(ExceptionEnum.WRONG_PASSWORD);

    }








}
