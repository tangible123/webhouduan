package com.web.demo.controller;

import com.web.demo.common.ApiRestResponse;
import com.web.demo.exception.ExceptionEnum;
import com.web.demo.service.RedisService;
import com.web.demo.service.SendJunkMailService;
import com.web.demo.service.UserService;
import com.web.demo.util.CodeUtil;
import com.web.demo.vo.EmailObject;
import com.web.demo.vo.ModifyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class FindPasswordController {

    @Autowired
    private SendJunkMailService sendJunkMailService;

    public String temp;
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    public  long Seconds = 60;

    public String code = "";  // 先写死 方便调试

    @PostMapping("/verified")
    @CrossOrigin
    public ApiRestResponse createCode(@RequestBody(required = false) EmailObject emailObject) {


        code = CodeUtil.keyUtils();  // 随机生成 验证码
        redisService.set(emailObject.getEmail(),code); // 将 邮箱 和 对应验证码 存入 redis
        redisService.expire(emailObject.getEmail(),Seconds);

         // 测试是否存入 redis
        System.out.println("测试code是否存入 redis"+redisService.get(emailObject.getEmail()));

        emailObject.setContent("您的修改密码验证码为"+code+"，验证码有效时长10分钟");
        System.out.println("email: "+code);
        //  验证码信息 传入 队列中
         sendJunkMailService.asynSendJunkMail(emailObject);

        // 这里 返回发送 成功 其实 不太严谨 ， 这里 将消息生产完  就说 消息发送成功 是不对的，需要从消费者那里获得 信息才能说 验证码发送成功
        return ApiRestResponse.success();
    }

    @CrossOrigin
    @PostMapping("/verifiedcode")
    public ApiRestResponse verifyCode(@RequestBody(required = false)EmailObject emailObject) {

         if(StringUtils.isEmpty(emailObject.getEmail())) {   // 发送的是 手机
             System.out.println("先验证能否接受到数据"+emailObject.getPhone()+"   "+emailObject.getCode());

             //  前端 点击 验证后 传过来 邮箱，然后通过邮箱在redis中寻找验证码 并返回，如果验证码过期则返回空
             String code = redisService.get(emailObject.getPhone());
             System.out.println("redis里面的验证码"+code);

             // 先判断是否过期
             if(StringUtils.isEmpty(code)) { // redis 里面验证码过期 返回过期 状态码
                 return  ApiRestResponse.error(ExceptionEnum.CODE_OUTDATE); // 验证码过期状态码 10010
             }
             if(!code.equals(emailObject.getCode())) {  // 从前端传过来的 验证码 与 redis 里面查询得到的验证码 不相同
                 return ApiRestResponse.error(ExceptionEnum.CODE_ERROR);   // 前端输入的 验证码错误  返回 10011
             }
             return ApiRestResponse.success();  // 最后的是成功的情况 返回 10000

         }else {
             System.out.println("先验证能否接受到数据" + emailObject.getEmail() + "   " + emailObject.getCode());

             //  前端 点击 验证后 传过来 邮箱，然后通过邮箱在redis中寻找验证码 并返回，如果验证码过期则返回空
             String code = redisService.get(emailObject.getEmail());
             System.out.println("redis里面的验证码" + code);

             // 先判断是否过期
             if (StringUtils.isEmpty(code)) { // redis 里面验证码过期 返回过期 状态码
                 return ApiRestResponse.error(ExceptionEnum.CODE_OUTDATE); // 验证码过期状态码 10010
             }
             if (!code.equals(emailObject.getCode())) {  // 从前端传过来的 验证码 与 redis 里面查询得到的验证码 不相同
                 return ApiRestResponse.error(ExceptionEnum.CODE_ERROR);   // 前端输入的 验证码错误  返回 10011
             }
             return ApiRestResponse.success();  // 最后的是成功的情况 返回 10000
         }
    }


}
