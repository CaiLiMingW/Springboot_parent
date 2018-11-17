package org.clm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.clm.Bean.Recruit;
import result.ServerResponse;

/**
 * <p>
 * 职位 服务类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
public interface IRecruitService extends IService<Recruit> {

    ServerResponse selectByKey(Integer page, Integer size, Recruit recruit);
}
