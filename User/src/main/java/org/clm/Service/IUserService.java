package org.clm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.clm.Bean.User;
import result.ServerResponse;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-21
 */
public interface IUserService extends IService<User> {

    void sendMsm(String mobile);

    ServerResponse register(User user, String code);

    User login(User user);
}
