package com.future.yw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.yw.model.entity.YwSalesback;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
public interface IYwSalesbackService extends IService<YwSalesback> {

    /**
     * 对商品销售进行退货处理
     * @param id    销售单ID
     * @param number    退货数量
     * @param remark    备注
     */
    void addSalesback(Integer id, Integer number, String remark);

}
