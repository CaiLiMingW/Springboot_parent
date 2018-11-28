package org.clm;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import result.Roles;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ccc
 * @date 2018/11/27 0027 下午 7:16
 */
@Component
@Slf4j
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("\n***********Zuul过滤器***********");
        //得到requestContext对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = requestContext.getRequest();
        //判断是否是预请求：使用的请求方式是OPTIONS
        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
            return null;
        }
        String uri = request.getRequestURI();
        //如果是登录操作,放行
        log.info("\n非登录操作{}",uri.indexOf("/login"));
        if (uri.indexOf("/login")>0){
            log.info("登录操作,放行{}:",uri.indexOf("/login"));
            return null;
        }
        //取出头信息
        String header = request.getHeader("Authorization");
        if (header!=null && header.startsWith("Bearer ")){
            String token = header.split(" ")[1];
            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims!=null){
                    String roles = String.valueOf(claims.get("roles"));
                    log.info("权限:\n{}",roles);
                    //||StringUtils.equals(roles,Roles.USER.getDesc())
                    if(StringUtils.equals(roles,Roles.ADMIN.getDesc())){
                        log.info("通过");
                        requestContext.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("令牌不正确");
            }
        }
        //保证后续操作不再执行
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(401);
        //设置响应正文的MIME类型和字符集
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");
        requestContext.setResponseBody("无权限");
        return null;
    }
}
