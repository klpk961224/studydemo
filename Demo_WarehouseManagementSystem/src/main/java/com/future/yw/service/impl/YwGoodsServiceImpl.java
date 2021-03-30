package com.future.yw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.yw.mapper.YwGoodsMapper;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.service.IYwGoodsService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Service
public class YwGoodsServiceImpl extends ServiceImpl<YwGoodsMapper, YwGoods> implements IYwGoodsService {

    @Override
    public boolean save(YwGoods entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(YwGoods entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public YwGoods getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public void deleteGoodsById(Integer id) {
        //根据商品id删除商品销售信息
        this.getBaseMapper().deleteSaleByGoodsId(id);
        //根据商品id删除商品销售退货信息
        this.getBaseMapper().deleteSaleBackByGoodsId(id);
        //根据商品id删除商品进货信息
        this.getBaseMapper().deleteInportByGoodsId(id);
        //根据商品id删除商品退货信息
        this.getBaseMapper().deleteOutportByGoodsId(id);
        //删除商品信息
        this.removeById(id);
    }

    /**
     * 查询所有库存预警商品
     * @return
     */
    @Override
    public List<YwGoods> loadAllWarning() {
        List<YwGoods> goods = baseMapper.loadAllWarning();
        return goods;
    }

}
