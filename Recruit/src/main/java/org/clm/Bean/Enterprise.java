package org.clm.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 企业
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_enterprise")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业简介
     */
    private String summary;

    /**
     * 企业地址
     */
    private String address;

    /**
     * 标签列表
     */
    private String labels;

    /**
     * 坐标
     */
    private String coordinate;

    /**
     * 是否热门
     */
    private String ishot;

    /**
     * LOGO
     */
    private String logo;

    /**
     * 职位数
     */
    private Integer jobcount;

    /**
     * URL
     */
    private String url;


}
