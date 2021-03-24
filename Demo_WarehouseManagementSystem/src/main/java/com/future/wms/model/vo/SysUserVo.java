package com.future.wms.model.vo;

import com.future.wms.model.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author evanliu
 * @create 2021-03-24 23:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserVo extends SysUser {

    private Integer page = 1;
    private Integer limit = 10;

    /**
     * 验证码
     */
    private String code;
}
