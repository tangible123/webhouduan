package com.web.demo.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.web.demo.service.RedisService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SMSUtil {

    @Autowired
    private RedisService redisService;

    public static String code1=null;
    public static long time1;


    public  void sendSMS(String tel) {
        String reStr = ""; //定义返回值
        // 短信应用SDK AppID  1400开头
        int appid = 1400587498;
        // 短信应用SDK AppKey
        String appkey = "136c3e78c5ed4bdb32492818bb22d759";
        // 短信模板ID，需要在短信应用中申请
        int templateId = 1173986 ;
        // 签名，使用的是签名内容，而不是签名ID
        String smsSign = "Sineb公众号";
        //随机生成四位验证码的工具类
        String code = CodeUtil.keyUtils();
        redisService.set(tel,code);
        redisService.expire(tel,60);

        code1=code;
        try {
            //参数，一定要对应短信模板中的参数顺序和个数，
            String[] params = {code};
            //创建ssender对象
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            //发送
            SmsSingleSenderResult result = ssender.sendWithParam("86", tel,templateId, params, smsSign, "", "");

            if(result.result!=0){
                reStr = "error";
            }
        /*
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            HttpSession session = request.getSession();
            //JSONObject存入数据
            JSONObject json = new JSONObject();
            json.put("Code", code);//存入验证码
            time1=System.currentTimeMillis();
            json.put("createTime", System.currentTimeMillis());//存入发送短信验证码的时间
            // 将验证码和短信发送时间码存入SESSION
            request.getSession().setAttribute("Code",code);
            request.getSession().setAttribute("MsCode", json);
            System.out.println(json);
            reStr = "success";*/
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }


    }


}

