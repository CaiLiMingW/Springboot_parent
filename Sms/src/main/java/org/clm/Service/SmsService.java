package org.clm.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ccc
 * @date 2018/11/20 0020 下午 5:01
 */
@Service
public class SmsService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void topic1(){
        rabbitTemplate.convertAndSend("topicExchanges","topic1","topic1");
    }

    public void topic2(){
        rabbitTemplate.convertAndSend("topicExchanges","topic2","topic2");
    }

    public void sendfanout(){
        rabbitTemplate.convertAndSend("fanout","","fanout测试");
    }

    public void sendDirect(){
        rabbitTemplate.convertAndSend("Direct","Direct模式");
    }

}
