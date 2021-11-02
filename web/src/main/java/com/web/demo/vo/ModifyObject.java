package com.web.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModifyObject implements Serializable{
    private String  phone;
    private String  email;
    private String  newPassWord;
    private String  confirmPassword;
}
