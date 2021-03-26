package com.future.wms.model.vo;

import com.future.wms.model.entity.SysNotice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author evanliu
 * @create 2021-03-26 22:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysNoticeVo extends SysNotice {

    private static final long serialVersionUID = 4960562861156872940L;
    private Integer page = 1;
    private Integer limit = 10;

    /**
     * 接受多个ID
     */
    private Integer[] ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
