package org.clm.Client;

import org.clm.Client.impl.LabelClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import result.ServerResponse;

/**
 * @author Ccc
 * @date 2018/11/22 0022 下午 2:20
 * 从Base服务调用功能,名称不能包含下划线
 */
@FeignClient(value = "Base",fallback = LabelClientImpl.class)
public interface LabelClient {

    @RequestMapping(value="/label/{labelId}", method = RequestMethod.GET)
    public ServerResponse findById(@PathVariable("labelId") String id);
}
