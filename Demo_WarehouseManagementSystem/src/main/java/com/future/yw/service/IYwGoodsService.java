package com.future.yw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.yw.model.entity.YwGoods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
public interface IYwGoodsService extends IService<YwGoods> {

    /**
     * 根据商品id删除商品
     * @param id
     */
    void deleteGoodsById(Integer id);

    /**
     * 加载所有的库存预警商品
     * @return
     */
    List<YwGoods> loadAllWarning();

}
