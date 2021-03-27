package com.future.wms.model.vo;

import com.future.wms.model.entity.SysDept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author evanliu
 * @create 2021-03-27 17:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDeptVo extends SysDept {

    private Integer page = 1;
    private Integer limit = 10;

}
