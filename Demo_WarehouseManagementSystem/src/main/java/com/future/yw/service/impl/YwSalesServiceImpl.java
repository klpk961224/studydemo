package com.future.yw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.yw.mapper.YwGoodsMapper;
import com.future.yw.mapper.YwSalesMapper;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwSales;
import com.future.yw.service.IYwSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Service
public class YwSalesServiceImpl extends ServiceImpl<YwSalesMapper, YwSales> implements IYwSalesService {

    @Autowired
    private YwGoodsMapper ywGoodsMapper;

    /**
     * 添加商品销售
     * @param entity    商品销售实体类
     * @return
     */
    @Override
    public boolean save(YwSales entity) {
        YwGoods goods = ywGoodsMapper.selectById(entity.getGoodsid());
        goods.setNumber(goods.getNumber() - entity.getNumber());
        //更新商品的库存信息
        ywGoodsMapper.updateById(goods);
        return super.save(entity);
    }

    /**
     * 更新商品销售
     * @param entity    商品销售实体类
     * @return
     */
    @Override
    public boolean updateById(YwSales entity) {
        //根据销售单ID查询销售单信息
        YwSales sales = baseMapper.selectById(entity.getId());
        YwGoods goods = ywGoodsMapper.selectById(entity.getGoodsid());
        //仓库商品数量=原库存-销售单修改之前的数量+修改之后的数量
        //     80  +40 -  50     30
        goods.setNumber(goods.getNumber() + sales.getNumber() - entity.getNumber());
        //更新商品
        ywGoodsMapper.updateById(goods);
        return super.updateById(entity);
    }

    /**
     * 删除商品销售信息
     * @param id    商品销售单ID
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        //根据商品销售单ID查询出销售单数据
        YwSales sales = baseMapper.selectById(id);
        YwGoods goods = ywGoodsMapper.selectById(sales.getGoodsid());
        //仓库商品数量=原库存+删除商品销售单的数量
        goods.setNumber(goods.getNumber() + sales.getNumber());
        ywGoodsMapper.updateById(goods);
        return super.removeById(id);
    }
}