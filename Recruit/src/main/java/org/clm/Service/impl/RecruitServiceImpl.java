package org.clm.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.clm.Bean.Recruit;
import org.clm.Dao.RecruitMapper;
import org.clm.Service.IRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ServerResponse;

import java.util.List;

/**
 * <p>
 * 职位 服务实现类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@Service
public class RecruitServiceImpl extends ServiceImpl<RecruitMapper, Recruit> implements IRecruitService {
    @Autowired
    private RecruitMapper recruitMapper;
    @Override
    public ServerResponse selectByKey(Integer page, Integer size, Recruit recruit) {
        if (page==null&&size==null){
            return ServerResponse.CreateBySuccessMessage( recruitMapper.selectByKey(page,size,recruit));
        }
        PageHelper.startPage(page,size);
        List<Recruit> recruitList = recruitMapper.selectByKey(page, size, recruit);
        PageInfo pageInfo = new PageInfo(recruitList);
        return ServerResponse.CreateBySuccessMessage(page);
    }
}
