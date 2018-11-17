package org.clm.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.clm.Bean.Recruit;

import java.util.List;

/**
 * <p>
 * 职位 Mapper 接口
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
public interface RecruitMapper extends BaseMapper<Recruit> {
    @Select("select * from tb_recruit")
    List<Recruit> selectAll();

    List<Recruit> selectByKey(Integer page, Integer size, Recruit recruit);
}
