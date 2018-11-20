package org.clm.Controller;

import org.clm.Service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.ServerResponse;

/**
 * @author Ccc
 * @date 2018/11/20 0020 下午 5:05
 */
@RestController
    @RequestMapping("/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;


    @GetMapping("/topic/1")
    public ServerResponse topic1(){
        smsService.topic1();
        return ServerResponse.CreateBySuccessMessage();
    }

    @GetMapping("/topic/2")
    public ServerResponse topic2(){
        smsService.topic2();
        return ServerResponse.CreateBySuccessMessage();
    }

    @GetMapping("/fanout")
    public ServerResponse sendfanout(){
        smsService.sendfanout();
        return ServerResponse.CreateBySuccessMessage();
    }

    @GetMapping("/direct")
    public ServerResponse sendDirect(){
        smsService.sendDirect();
        return ServerResponse.CreateBySuccessMessage();
    }
}
