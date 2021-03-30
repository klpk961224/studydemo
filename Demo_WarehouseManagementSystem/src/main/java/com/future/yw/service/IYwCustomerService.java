package com.future.yw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.yw.model.entity.YwCustomer;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
public interface IYwCustomerService extends IService<YwCustomer> {

    /**
     * 根据客户id删除客户
     * @param id    客户id
     */
    void deleteCustomerById(Integer id);

}
