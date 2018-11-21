package org.clm.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 管理员
 * </p>
 *
 * @author Ccc
 * @since 2018-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 登陆名称
     */
    private String loginname;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String state;


}
