package com.future.yw.model.vo;

import com.future.yw.model.entity.YwProvider;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author evanliu
 * @create 2021-03-30 18:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class YwProviderVo extends YwProvider {

    /**
     * 分页参数，当前是第一页，每页10条数据
     */
    private Integer page = 1;
    private Integer limit = 10;

    /**
     * 批量删除供应商，存放供应商ID的数组
     */
    private Integer[] ids;

}
