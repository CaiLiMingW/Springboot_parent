package org.clm.Controller;

import org.clm.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.ServerResponse;

/**
 * @author Ccc
 * @date 2018/11/20 0020 下午 10:53
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ServerResponse sendmsm(String mobile){
        userService.sendMsm(mobile);
        return ServerResponse.CreateBySuccessMessage();
    }

}
