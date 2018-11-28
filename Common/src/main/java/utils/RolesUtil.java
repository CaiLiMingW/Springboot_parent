package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import result.Roles;
import result.ServerResponse;
import result.StatusCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ccc
 * @date 2018/11/27 0027 下午 9:15
 */
@Slf4j
public class RolesUtil {
    public ServerResponse CheckRolesUser(HttpServletRequest request){
        String role = String.valueOf(request.getAttribute(Roles.Key.getDesc()));
        log.info("权限:\n{}",role);
        //无权限
        if (StringUtils.equals(role,Roles.ADMIN.getDesc())||StringUtils.equals(role,Roles.USER.getDesc())){
            log.info("无权限:\n{}",role);
            return null;
        }
        return ServerResponse.CreateByErrorCode(StatusCode.LOGINERROR.getCode(),"账号密码错误或登录已失效");

    }

    public ServerResponse CheckRolesAdmin(HttpServletRequest request){
        String role = String.valueOf(request.getAttribute(Roles.Key.getDesc()));
        log.info("Ad权限:\n{}",role);
        //无权限
        if (StringUtils.equals(role,Roles.ADMIN.getDesc())){
            log.info("Ad有权限:\n{}",role);
            return null;
        }
        return ServerResponse.CreateByErrorCode(StatusCode.ACCESSERROR.getCode());
    }
}
