package org.clm.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.clm.Bean.Admin;
import org.clm.Dao.AdminMapper;
import org.clm.Service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import result.Roles;
import result.ServerResponse;
import result.StatusCode;
import result.Subject;
import utils.IdWorker;
import utils.JwtUtil;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-21
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ServerResponse addAdmin(Admin admin) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("loginname",admin.getLoginname());
        Integer res = adminMapper.selectCount(queryWrapper);
        if (res != null && res > 0){
            return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode(),"管理员账号已存在");
        }

        admin.setId(String.valueOf(idWorker.nextId()));
        //密码加密
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        res = adminMapper.insert(admin);
        if (res > 0){
            return ServerResponse.CreateBySuccessMessage();
        }
        return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode(),"添加管理员失败");
    }

    @Override
    public ServerResponse login(String loginname, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("loginname", loginname);

        Admin admin = adminMapper.selectOne(queryWrapper);
        log.info("账号:{}",admin.getLoginname());
        if (admin != null && bCryptPasswordEncoder.matches(password, admin.getPassword())){
            String jwtToken = jwtUtil.createJWT(loginname, Subject.LOGINSUCCESS.getDesc(), Roles.ADMIN.getDesc());
            return ServerResponse.CreateBySuccessMessage(jwtToken);
        }else if (admin != null){
            return ServerResponse.CreateByErrorCode(StatusCode.LOGINERROR.getCode(),StatusCode.LOGINERROR.getDesc());
        }
        return ServerResponse.CreateByErrorCode(StatusCode.ACCESSERROR.getCode(),StatusCode.ACCESSERROR.getDesc());
    }
}
