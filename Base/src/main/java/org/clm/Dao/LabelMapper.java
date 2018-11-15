package org.clm.Dao;

import org.clm.bean.Label;

public interface LabelMapper {
    int deleteByPrimaryKey(String id);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);
}