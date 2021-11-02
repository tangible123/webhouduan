package com.web.demo;

import com.web.demo.service.SendJunkMailService;
import com.web.demo.service.UserService;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@SpringBootTest
@Component
@Configurable
@EnableScheduling
class DemoApplicationTests {

    @Autowired
   SendJunkMailService sendJunkMailService;

    @Autowired
    private UserService userService;

    @Resource
    private RedisTemplate<String,String> redisT;


    @Test
    public  void test() {

       Integer idx =  userService.checkUserName("321");
       System.out.println(idx);
    }






}
