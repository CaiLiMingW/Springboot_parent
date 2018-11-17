package org.clm.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 专栏
 * </p>
 *
 * @author Ccc
 * @since 2018-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_column")
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 专栏名称
     */
    private String name;

    /**
     * 专栏简介
     */
    private String summary;

    /**
     * 用户ID
     */
    private String userid;

    /**
     * 申请日期
     */
    private LocalDateTime createtime;

    /**
     * 审核日期
     */
    private LocalDateTime checktime;

    /**
     * 状态
     */
    private String state;


}
