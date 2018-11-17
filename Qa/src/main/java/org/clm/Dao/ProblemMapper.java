package org.clm.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.clm.Bean.Problem;

import java.util.List;

/**
 * <p>
 * 问题 Mapper 接口
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
public interface ProblemMapper extends BaseMapper<Problem> {

    List<Problem> selectByKey(Problem problem);

    @Select("select * from tb_problem p where p.id in(select problemid from tb_pl where labelid = '?1')order by p.replytime desc")
    List<Problem> selectNewList(@Param("labelid")String  labelid);

    @Select("select * from tb_problem p where p.id in(select problemid from tb_pl where labelid = '?1')order by p.reply desc")
    List<Problem> selectHotList(@Param("labelid")String  labelid);

    @Select("select * from tb_problem p where p.id in(select problemid from tb_pl where labelid = '?1')and reply = 0 order by p.reply desc")
    List<Problem> selectWati(String labelid);
}
