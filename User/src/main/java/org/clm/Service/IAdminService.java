package org.clm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.clm.Bean.Admin;
import result.ServerResponse;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-21
 */
public interface IAdminService extends IService<Admin> {

    ServerResponse addAdmin(Admin admin);

    ServerResponse login(String loginname, String password);
}
