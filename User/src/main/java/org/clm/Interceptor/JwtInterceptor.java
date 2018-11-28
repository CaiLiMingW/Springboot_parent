package org.clm.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import result.Roles;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ccc
 * @date 2018/11/27 0027 下午 3:50
 */
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor{
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        log.info("拦截请求:\n{}", request.getRequestURI());
        String header = request.getHeader("Authorization");
        log.info("消息头:{}\n",header);
        //1.判断消息头
        if (StringUtils.isNotEmpty(header)){
            //2.消息头是否被修改
            if (header.startsWith("Bearer ")){
                //Bearer eyJhbGciOiJIUzI1N......
                String token = header.split(" ")[1];
                try {
                    //3.获取token中的权限信息
                    String roles = String.valueOf(jwtUtil.parseJWT(token).get("roles"));
                    log.info("拦截器权限:{}\n",roles);
                    request.setAttribute(Roles.Key.getDesc(),roles);
                    return true;
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确");
                }finally {
                    return true;
                }
            }
        }
        return true;
    }
}
