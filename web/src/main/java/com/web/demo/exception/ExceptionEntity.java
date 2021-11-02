package com.web.demo.exception;

/**
 * 异常码 code
 * 异常信息 msg
 */

public class ExceptionEntity extends Exception{

     private final Integer code; // 错误类型的状态码
    private  final String message;  //

    public ExceptionEntity(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    public  ExceptionEntity(ExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }



}
