package com.web.demo.service;

import com.web.demo.vo.EmailObject;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.Destination;
@Service
public class AyCodeProduct {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public  void sendMessageToQueue(Destination destination, EmailObject emailObject){
        System.out.println(emailObject.getSubject()+"生产者这里也收到了参数");
        jmsMessagingTemplate.convertAndSend(destination,emailObject);  //  将验证码入队
        System.out.println("成功入队");
    }


}
