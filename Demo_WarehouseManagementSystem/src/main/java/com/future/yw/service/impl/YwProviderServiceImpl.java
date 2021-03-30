package com.future.yw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.yw.mapper.YwGoodsMapper;
import com.future.yw.mapper.YwProviderMapper;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwProvider;
import com.future.yw.service.IYwProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
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
public class YwProviderServiceImpl extends ServiceImpl<YwProviderMapper, YwProvider> implements IYwProviderService {

    @Autowired
    private YwGoodsMapper ywGoodsMapper;

    @Override
    public boolean save(YwProvider entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(YwProvider entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public YwProvider getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    /**
     * 根据供应商id删除供应商
     * @param id    供应商id
     */
    @Override
    public void deleteProviderById(Integer id) {
        //根据供应商id查询出商品id
        QueryWrapper<YwGoods> queryWrapper = new QueryWrapper<YwGoods>();
        queryWrapper.eq("providerid", id);
        List<YwGoods> goods = ywGoodsMapper.selectList(queryWrapper);
        for (YwGoods good : goods) {
            //获取一个商品id
            Integer id1 = good.getId();
            //根据商品id删除商品销售信息
            ywGoodsMapper.deleteSaleByGoodsId(id1);
            //根据商品id删除商品销售退货信息
            ywGoodsMapper.deleteSaleBackByGoodsId(id1);
        }
        //根据供应商id删除商品退货信息
        this.getBaseMapper().deleteOutPortByProviderId(id);
        //根据供应商id删除商品进货信息
        this.getBaseMapper().deleteInportByProviderId(id);
        //根据供应商id删除商品
        this.getBaseMapper().deleteGoodsByProviderId(id);
        //删除供应商
        this.removeById(id);
    }

}
