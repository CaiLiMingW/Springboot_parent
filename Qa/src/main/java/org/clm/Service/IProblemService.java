package org.clm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.clm.Bean.Problem;
import result.ServerResponse;

/**
 * <p>
 * 问题 服务类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
public interface IProblemService extends IService<Problem> {

    ServerResponse selectByKeyPage(Integer page, Integer size, Problem problem);

    ServerResponse selectNewList(Integer page, Integer size, String label);

    ServerResponse selectHotList(Integer page, Integer size, String label);

    ServerResponse selectWaitList(Integer page, Integer size, String labelid);
}
