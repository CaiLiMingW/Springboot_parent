package org.clm.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.clm.Bean.Enterprise;

import java.util.List;

/**
 * <p>
 * 企业 Mapper 接口
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

    List<Enterprise> selectByKey(Enterprise enterprise);
}
