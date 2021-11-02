package com.web.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhoneObject implements Serializable {
    private  String phone;
    private  String code;
}
