package com.future.wms.model.vo;

import com.future.wms.model.entity.SysPermission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author evanliu
 * @create 2021-03-25 17:19
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermissionVo extends SysPermission {

    private static final long serialVersionUID = -5059346549971659184L;
    private Integer page = 1;
    private Integer limit = 10;
}
