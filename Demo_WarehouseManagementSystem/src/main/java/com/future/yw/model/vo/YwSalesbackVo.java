package com.future.yw.model.vo;

import com.future.yw.model.entity.YwSalesback;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author evanliu
 * @create 2021-03-30 18:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class YwSalesbackVo extends YwSalesback {

    private static final long serialVersionUID = -7306206199028838425L;
    private Integer page = 1;

    private Integer limit = 10;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
