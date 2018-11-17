package org.clm.Dao;

import org.apache.ibatis.annotations.Select;
import org.clm.bean.Label;

import java.util.List;

public interface LabelMapper {
    int deleteByPrimaryKey(String id);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    @Select("SELECT * FROM tb_label")
    List<Label> selectAll();

    List<Label> selectByLable(Label label);
}