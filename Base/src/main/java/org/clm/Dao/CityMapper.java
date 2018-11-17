package org.clm.Dao;

import org.apache.ibatis.annotations.Select;
import org.clm.bean.City;

import java.util.List;

public interface CityMapper {
    int deleteByPrimaryKey(String id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    @Select("select * from tb_city")
    List<City> selectAll();

    List<City> selectByKeyword(City city);
}