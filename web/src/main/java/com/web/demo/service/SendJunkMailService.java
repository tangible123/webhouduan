package com.web.demo.service;

import com.web.demo.vo.EmailObject;

public interface SendJunkMailService {
    void asynSendJunkMail(EmailObject emailObject);

    boolean sendJunkMail(String toMail,String subject,String content);
}

