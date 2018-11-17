package org.clm.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.clm.Bean.Enterprise;
import org.clm.Dao.EnterpriseMapper;
import org.clm.Service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ServerResponse;

import java.util.List;

/**
 * <p>
 * 企业 服务实现类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements IEnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public ServerResponse selectKeyWithPage(Integer page, Integer size, Enterprise enterprise) {
        if (page!=null&&size!=null){
            PageHelper.startPage(page,size);
            List<Enterprise> list = enterpriseMapper.selectByKey(enterprise);
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.CreateBySuccessMessage(pageInfo);
        }
        return ServerResponse.CreateBySuccessMessage(enterpriseMapper.selectByKey(enterprise));
    }
}
