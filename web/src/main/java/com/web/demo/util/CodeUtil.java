package com.web.demo.util;

import java.util.Random;

public class CodeUtil {
    public static String keyUtils() {

        Random random = new Random();
        Integer code = random.nextInt(9000)+1000;  // 生成1000到10000的整形验证码
        return code.toString();
    }
}
