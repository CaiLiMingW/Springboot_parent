package org.clm.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.clm.Bean.User;
import org.clm.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import result.ServerResponse;
import result.StatusCode;
import utils.IdWorker;
import utils.JwtUtil;
import utils.RolesUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author Ccc
 * @since 2018-11-21
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RolesUtil rolesUtil;

    @PostMapping
    public ServerResponse addUser(@RequestBody User user){
        //检查权限
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile",user.getMobile());
        User u = (User)iUserService.getObj(queryWrapper);
        if (u!=null && StringUtils.equals(u.getMobile(),user.getMobile())){
            return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode(),"手机号已存在");
        }

        user.setId(String.valueOf(idWorker.nextId()));
        //密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        iUserService.save(user);
        return ServerResponse.CreateBySuccessMessage();
    }

    @GetMapping
    public ServerResponse getAllUser(){
        //检查权限
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        return ServerResponse.CreateBySuccessMessage(iUserService.list(null));
    }

    //todo 修改登录
    @PostMapping("/login")
    public ServerResponse login(@RequestBody User user){
            return iUserService.login(user);
    }

    @PostMapping("/sendsms/{mobile}")
    public ServerResponse sendmsm(@PathVariable("mobile") String mobile){
        iUserService.sendMsm(mobile);
        return ServerResponse.CreateBySuccessMessage();
    }

    @PostMapping("/register/{code}")
    public ServerResponse register(@RequestBody User user,@PathVariable("code")String code){
        user.setId(String.valueOf(idWorker.nextId()));
        //密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ServerResponse register = iUserService.register(user, code);

        return register;
    }

    @GetMapping("/{id}")
    public ServerResponse getUserById(@PathVariable("id")String id){
        //检查权限
        ServerResponse serverResponse = rolesUtil.CheckRolesUser(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        return ServerResponse.CreateBySuccessMessage(iUserService.getById(id));
    }

    @PutMapping("/{id}")
    public ServerResponse updateUserById(@PathVariable("id")String id,@RequestBody User user){
        //检查权限
        ServerResponse serverResponse = rolesUtil.CheckRolesUser(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        user.setId(id);
        boolean b = iUserService.updateById(user);
        if (b){
            return ServerResponse.CreateBySuccessMessage();
        }
        return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode(),"修改用户失败");
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteUserById(@PathVariable("id")String id){
        //检查权限
        ServerResponse serverResponse = rolesUtil.CheckRolesAdmin(request);
        if (serverResponse!=null){
            return serverResponse;
        }

        boolean res = iUserService.removeById(id);
        if (res){
            return ServerResponse.CreateBySuccessMessage();
        }
        return ServerResponse.CreateByErrorMessage();
    }

    //todo 修改用户登录信息
    @GetMapping("/info/{id}")
    public ServerResponse getUserInfo(@PathVariable("id")String id){
        //检查权限
        ServerResponse serverResponse = rolesUtil.CheckRolesUser(request);
        if (serverResponse!=null){
            return serverResponse;
        }
        return ServerResponse.CreateBySuccessMessage(iUserService.getById(id));
    }
}
