package org.clm.Client.impl;

import org.clm.Client.LabelClient;
import org.springframework.stereotype.Component;
import result.ServerResponse;
import result.StatusCode;

/**
 * @author Ccc
 * @date 2018/11/27 0027 上午 9:08
 */
@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public ServerResponse findById(String id) {
        return ServerResponse.CreateByErrorCode(StatusCode.ERROR.getCode(),"触发熔断器");
    }
}
