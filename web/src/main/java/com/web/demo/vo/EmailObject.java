package com.web.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailObject implements Serializable {
    private  String email;
    private final String subject = "修改密码验证码";
    private  String content;
    private String code;
    private String phone;
}
