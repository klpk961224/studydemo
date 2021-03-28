package com.future.wms.model.vo;

import com.future.wms.model.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author evanliu
 * @create 2021-03-28 15:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleVo extends SysRole {

    private static final long serialVersionUID = -3949321991475046251L;
    private Integer page = 1;
    private Integer limit = 10;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
