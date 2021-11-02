package com.web.demo.exception;


    /**
     * 定义异常枚举
     */
    public enum ExceptionEnum {
        //业务异常 10000
        NEED_USER_NAME(10001,"用户名不能为空"),
        NEED_PASSWORD(10002,"密码不能为空"),
        PASSWORD_TO_SHORT(10003,"密码长度不小于8位"),
        NAME_EXISTED(10004,"不予许重名"),
        INSERT_FAILED(10005,"插入失败，请重试"),
        WRONG_PASSWORD(10006,"密码错误"),
        NEED_LOGIN(10007,"用户未登录"),
        UPDATE_FAILED(10008,"更新失败"),
        NEED_ADMIN(10009,"无管理员权限"),
        CODE_OUTDATE(10010,"验证码过期"),
        CODE_ERROR(10011,"验证码错误"),
        USERNAME_REPEAT(10012,"用户名重复"),
        USERNUM_REPEAT(10013,"学号重复"),
        EMIAL_REPEAT(10014,"邮箱重复"),
        PHONE_REPEAT(10015,"手机号重复"),
        //系统异常 20000
        SYSTEM_ERROR(20000,"系统异常");
        /**
         * 异常码 code
         * 异常信息 msg
         */
        Integer code;
        String msg;

        ExceptionEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


