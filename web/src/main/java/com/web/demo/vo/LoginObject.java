package com.web.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginObject implements Serializable{

    private  String  userNum;
    private  String  password;

}
