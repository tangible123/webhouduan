package com.web.demo.controller;

import com.web.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String IndexPage() {
        return "login";
    }


    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String checkLogin(HttpSession session, @RequestParam String stuNum,@RequestParam String password,Model model) {

         Integer judge = userService.CheckLogin(stuNum,password);
         if(judge!=-1) // 成功登录
         {
             return "index";
         }
         return "error";
    }



}
