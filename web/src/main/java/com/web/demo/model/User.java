package com.web.demo.model;

// java bean

import lombok.Data;

@Data
public class User {
    private String userNum;
    private String username;
    private String phone;
    private String password;
    private String email;
}
