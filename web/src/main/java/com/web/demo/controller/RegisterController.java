package com.web.demo.controller;

import com.web.demo.common.ApiRestResponse;
import com.web.demo.exception.ExceptionEnum;
import com.web.demo.service.UserService;
import com.web.demo.util.SMSUtil;
import com.web.demo.vo.PhoneObject;
import com.web.demo.vo.RegisterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/registed")
public class RegisterController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/username")
    @ResponseBody
    public ApiRestResponse checkUserName (@RequestBody(required = false) RegisterObject registerObject){

           if(userService.checkUserName(registerObject.getUsername())>=1) {  // 重复
               return  ApiRestResponse.error(ExceptionEnum.USERNAME_REPEAT);  // 10012
           }
         return ApiRestResponse.success();
    }

}
