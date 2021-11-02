package com.web.demo.util;


import com.web.demo.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 描述：MD5加密工具
 */
public class MD5Utils {
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        //传入算法MD5
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //传入的参数还需要进行base64的转码，方便存储
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes()));
        // 简单密码后面 加盐  Constant.SALT  使得密码变得复杂
    }
}

