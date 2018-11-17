package org.clm.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.clm.Bean.Problem;
import org.clm.Dao.ProblemMapper;
import org.clm.Service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ServerResponse;

import java.util.List;

/**
 * <p>
 * 问题 服务实现类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public ServerResponse selectByKeyPage(Integer page, Integer size, Problem problem) {
        if (page != null && size != null){
            PageHelper.startPage(page,size);
            List<Problem> list = problemMapper.selectByKey(problem);
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.CreateBySuccessMessage(pageInfo);
        }
        return ServerResponse.CreateBySuccessMessage(problemMapper.selectByKey(problem));
    }

    @Override
    public ServerResponse selectNewList(Integer page, Integer size, String label) {
        if (page != null && size != null){
            PageHelper.startPage(page,size);;
            List<Problem> list = problemMapper.selectNewList(label);
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.CreateBySuccessMessage(pageInfo);
        }
        return ServerResponse.CreateBySuccessMessage(problemMapper.selectNewList(label));
    }

    @Override
    public ServerResponse selectHotList(Integer page, Integer size, String label) {
        if (page != null && size != null){
            PageHelper.startPage(page,size);;
            List<Problem> list = problemMapper.selectHotList(label);
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.CreateBySuccessMessage(pageInfo);
        }
        return ServerResponse.CreateBySuccessMessage(problemMapper.selectHotList(label));
    }

    @Override
    public ServerResponse selectWaitList(Integer page, Integer size, String labelid) {
        if (page != null && size != null){
            PageHelper.startPage(page,size);;
            List<Problem> list = problemMapper.selectWati(labelid);
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.CreateBySuccessMessage(pageInfo);
        }
        return ServerResponse.CreateBySuccessMessage(problemMapper.selectWati(labelid));
    }


}
