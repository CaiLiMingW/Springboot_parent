package org.clm.Service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Ccc
 * @date 2018/11/20 0020 下午 5:02
 */
@Component

public class Customer1 {

    @RabbitHandler
    @RabbitListener(queues = "fanout1")
    public void fanout1(String message){
        System.out.println("fanout模式接收到:"+message);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout2")
    public void fanout2(String message){
        System.out.println("fanout模式接收到:"+message);
    }

    @RabbitHandler
    @RabbitListener(queues = "Direct")
    public void Direct(String message){
        System.out.println("Direct模式接收到:"+message);
    }

    @RabbitHandler
    @RabbitListener(queues = "topic1")
    public void topic1(String message){
        System.out.println("Topic模式接收到:"+message);
    }

    @RabbitHandler
    @RabbitListener(queues = "topic2")
    public void topic2(String message){
        System.out.println("Topic模式接收到:"+message);
    }

}
