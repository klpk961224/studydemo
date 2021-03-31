package com.future.yw.model.vo;

import com.future.yw.model.entity.YwCustomer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author evanliu
 * @create 2021-03-30 18:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class YwCustomerVo extends YwCustomer {

    private static final long serialVersionUID = 1849884516902117595L;
    /**
     * 分页参数，当前是第一页，每页10条数据
     */
    private Integer page = 1;
    private Integer limit = 10;

    /**
     * 批量删除客户，存放客户ID的数组
     */
    private Integer[] ids;

}
