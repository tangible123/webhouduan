package com.web.demo.service;


import com.web.demo.vo.EmailObject;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.mail.internet.MimeMessage;
@Service
public class SendJunkMailServiceImpl  implements  SendJunkMailService{
    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private  String from;

    @Override
    public boolean sendJunkMail(String toMail,String subject,String content){  // 直接发送验证码
        try {
            MimeMessage messageM = this.mailSender.createMimeMessage();
            MimeMessageHelper message=new MimeMessageHelper(messageM);
            message.setTo(toMail);
            message.setSubject(subject);
            message.setText(content);
            message.setFrom(from);
            mailSender.send(messageM);
        }catch (Exception ex){
            return Boolean.FALSE;
        }
        return  Boolean.TRUE;
    }

    //队列
    private  static Destination destination=new ActiveMQQueue("ay.queue.asyn.save");

    @Resource
    private AyCodeProduct ayCodeProduct;  // 发送验证码 生产者

    public void asynSendJunkMail(EmailObject emailObject) {
        System.out.println("接受到参数");
        System.out.println("看看有没有邮箱： "+emailObject.getEmail());
        ayCodeProduct.sendMessageToQueue(destination,emailObject);  // 将发送验证码的信息 发送到队列里面
    }

}