package org.clm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.clm.Bean.Enterprise;
import result.ServerResponse;

/**
 * <p>
 * 企业 服务类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
public interface IEnterpriseService extends IService<Enterprise> {

    ServerResponse selectKeyWithPage(Integer page, Integer size, Enterprise enterprise);
}
