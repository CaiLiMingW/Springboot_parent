package org.clm.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ccc
 * @since 2018-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_pl")
public class Pl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问题ID
     */
    private String problemid;

    /**
     * 标签ID
     */
    private String labelid;


}
