package org.clm.Service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.clm.Service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Ccc
 * @date 2018/11/20 0020 下午 7:13
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsm(String mobile) {
        Random random = new Random();
        //随机生成验证码
        Integer code = random.nextInt(9999);
        if (code < 1000){
            code+=1000;
        }
        log.info("\n生成验证码{}",code);
        redisTemplate.opsForValue().set("smsCode_"+mobile,code,60*10, TimeUnit.SECONDS);
        Map map = new HashMap();
        map.put("mobile",mobile);
        map.put("code",code);
        rabbitTemplate.convertAndSend("sms",map);
        log.info("\n验证码生成成功:mobile={},code={}",mobile,code);
    }
}
