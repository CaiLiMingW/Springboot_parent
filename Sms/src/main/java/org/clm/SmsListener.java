package org.clm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Ccc
 * @date 2018/11/20 0020 下午 11:18
 */
@Component
@RabbitListener(queues = "sms")
@Slf4j
public class SmsListener {

    @RabbitHandler
    public void sendmsm(Map message){
        log.info("消费者获取验证码:mobile={},code={}",message.get("mobile"),message.get("code"));
    }
}
