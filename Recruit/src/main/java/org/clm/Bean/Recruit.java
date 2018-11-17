package org.clm.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 职位
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_recruit")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recruit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 职位名称
     */
    private String jobname;

    /**
     * 薪资范围
     */
    private String salary;

    /**
     * 经验要求
     */
    private String condition;

    /**
     * 学历要求
     */
    private String education;

    /**
     * 任职方式
     */
    private String type;

    /**
     * 办公地址
     */
    private String address;

    /**
     * 企业ID
     */
    private String eid;

    /**
     * 创建日期
     */
    private LocalDateTime createtime;

    /**
     * 状态
     */
    private String state;

    /**
     * 网址
     */
    private String url;

    /**
     * 标签
     */
    private String label;

    /**
     * 职位描述
     */
    private String content1;

    /**
     * 职位要求
     */
    private String content2;


}
