package com.web.demo.service;
import com.web.demo.vo.EmailObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class AyCodeConsumer {

    @Resource
    private  SendJunkMailService sendJunkMailService;

    @JmsListener(destination = "ay.queue.asyn.save")
    public void receiveQueue(EmailObject emailObject){

        Boolean judge = sendJunkMailService.sendJunkMail(emailObject.getEmail(),emailObject.getSubject(),emailObject.getContent());
        if(judge)
        System.out.println("消费了一个资源");
        else  System.out.println("消费失败");
    }

}
