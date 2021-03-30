package com.future.yw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.yw.model.entity.YwProvider;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
public interface IYwProviderService extends IService<YwProvider> {

    /**
     * 根据供应商ID删除供应商
     * @param id
     */
    void deleteProviderById(Integer id);

}
