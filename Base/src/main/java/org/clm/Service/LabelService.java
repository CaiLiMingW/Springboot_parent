package org.clm.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.clm.Dao.LabelMapper;
import org.clm.bean.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ServerResponse;
import utils.IdWorker;

import java.util.List;

/**
 * @author Ccc
 * @date 2018/11/15 0015 下午 8:25
 */
@Service
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     * @return
     */
    public List<Label> selectAllLable(){
        return labelMapper.selectAll();
    }

    /**
     * 根据Id查询标签
     * @param id
     * @return
     */
    public Label selectByIdLable(String id){
        return labelMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加标签
     * @param label
     */
    public void insertLable(Label label){
        labelMapper.insertSelective(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void deleteByIdLable(String id){
        int i = labelMapper.deleteByPrimaryKey(id);
    }

    public void updateLable(Label label){
        labelMapper.updateByPrimaryKeySelective(label);
    }

    public ServerResponse selectByIdLable(Integer page, Integer size, Label label) {
        StringBuilder labelname = new StringBuilder();
        labelname.append("%").append(label.getLabelname()).append("%");
        label.setLabelname(String.valueOf(labelname));
        if (page != null && size != null){
            PageHelper.startPage(page,size);
            List<Label> list = labelMapper.selectByLable(label);
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.CreateBySuccessMessage(pageInfo);
        }
        return ServerResponse.CreateBySuccessMessage(labelMapper.selectByLable(label));
    }
}
