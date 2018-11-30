package org.clm.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.clm.Bean.User;
import org.clm.Dao.UserMapper;
import org.clm.Service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import result.Roles;
import result.ServerResponse;
import result.StatusCode;
import result.Subject;
import utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-21
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void sendMsm(String mobile) {
        Random random = new Random();
        //随机生成验证码
        Integer code = random.nextInt(9999);
        if (code < 1000){
            code+=1000;
        }
        log.info("\ncreate code:{}",code);
        redisTemplate.opsForValue().set("smsCode_"+mobile, String.valueOf(code),60*10, TimeUnit.SECONDS);
        Map<String,String> map = new HashMap();
        map.put("mobile",mobile);
        map.put("code",String.valueOf(code));
        rabbitTemplate.convertAndSend("sms",map);
        log.info("\n验证码生成成功:mobile={},code={}",mobile,code);
    }

    @Override
    public ServerResponse register(User user, String code) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile",user.getMobile());
        Integer res = userMapper.selectCount(queryWrapper);
        if (res > 0){
            return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode(),"手机已存在");
        }
        String ourcode = String.valueOf(redisTemplate.opsForValue().get("smsCode_" + user.getMobile()));
        if (ourcode==null||!StringUtils.equals(ourcode,code)){
            return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode(),"验证码错误");
        }
        //密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        int rescow = userMapper.insert(user);
        if (rescow > 0){
            return ServerResponse.CreateBySuccessMessage();
        }
        return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode(),"注册失败");
    }

    @Override
    public ServerResponse login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile",user.getMobile());
        User u = userMapper.selectOne(queryWrapper);
        //BCRY验证密码
        if (u!=null && bCryptPasswordEncoder.matches(user.getPassword(),u.getPassword())){
            String token = jwtUtil.createJWT(u.getMobile(), Subject.LOGINSUCCESS.getDesc(), Roles.USER.getDesc());
            Map map = new HashMap();
            map.put("token",token);
            map.put("name",u.getNickname());
            map.put("avatar",u.getAvatar());
            return ServerResponse.CreateBySuccessMessage(map);
        }
        return ServerResponse.CreateByErrorCode(StatusCode.LOGINERROR.getCode());
    }
}
