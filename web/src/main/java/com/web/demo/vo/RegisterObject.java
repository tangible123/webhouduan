package com.web.demo.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class RegisterObject implements Serializable {
    private String userNum;
    private String username;
    private String phone;
    private String email;
    private String password;


}
