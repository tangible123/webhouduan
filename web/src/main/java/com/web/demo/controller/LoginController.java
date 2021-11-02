package com.web.demo.controller;

import com.web.demo.common.ApiRestResponse;
import com.web.demo.exception.ExceptionEnum;
import com.web.demo.service.RedisService;
import com.web.demo.util.SMSUtil;
import com.web.demo.vo.EmailObject;
import com.web.demo.vo.PhoneObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private RedisService redisService;

    public SMSUtil smsUtil;

    //点击发送验证码
    @CrossOrigin
    @PostMapping("/sendMs")
    @ResponseBody
    public ApiRestResponse sendMs (HttpServletRequest request, @RequestBody(required = false)PhoneObject phoneObject){
        String phoneNumber = phoneObject.getPhone();

        if(phoneNumber!=null&&!phoneNumber.equals("")){
            smsUtil.sendSMS(phoneNumber);
            return ApiRestResponse.success();
        }else{
            return  ApiRestResponse.error(ExceptionEnum.SYSTEM_ERROR);
        }
    }

    //登录验证
    @PostMapping("/register")
    @ResponseBody
    public Object register(HttpServletRequest request, String Code) {
        JSONObject json = (JSONObject)request.getSession().getAttribute("MsCode");
        if(!json.getString("Code").equals(Code)){
            return "验证码错误";
        }
        //我这里模拟了一分钟
        if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 1){
            return "验证码过期";
        }
        //将用户信息存入数据库、这里省略
        return "success";
    }




}
