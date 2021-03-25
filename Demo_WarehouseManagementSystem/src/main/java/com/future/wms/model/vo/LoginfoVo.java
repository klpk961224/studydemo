package com.future.wms.model.vo;

import com.future.wms.model.entity.SysLoginfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author evanliu
 * @create 2021-03-25 23:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginfoVo extends SysLoginfo {

    private static final long serialVersionUID = 6294447142131487136L;
    private Integer page = 1;
    private Integer limit = 10;

    /**
     * 接收多个id
     */
    private Integer[] ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
