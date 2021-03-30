package com.future.yw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.yw.mapper.YwGoodsMapper;
import com.future.yw.mapper.YwImportMapper;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwImport;
import com.future.yw.service.IYwImportService;
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
public class YwImportServiceImpl extends ServiceImpl<YwImportMapper, YwImport> implements IYwImportService {

    @Autowired
    private YwGoodsMapper ywGoodsMapper;

    /**
     * 保存商品进货
     * @param entity
     * @return
     */
    @Override
    public boolean save(YwImport entity) {
        //根据商品ID查询商品
        YwGoods goods = ywGoodsMapper.selectById(entity.getGoodsid());
        goods.setNumber(goods.getNumber() + entity.getNumber());
        ywGoodsMapper.updateById(goods);
        //保存进货信息
        return super.save(entity);
    }

    /**
     * 更新商品进货
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(YwImport entity) {
        //根据进货ID查询进货信息
        YwImport ywImport = baseMapper.selectById(entity.getId());
        //根据商品ID查询商品信息
        YwGoods goods = ywGoodsMapper.selectById(entity.getGoodsid());
        //库存算法  当前库存-进货单修改之前的数量+修改之后的数量
        goods.setNumber(goods.getNumber() - ywImport.getNumber() + entity.getNumber());
        ywGoodsMapper.updateById(goods);
        //更新进货单
        return super.updateById(entity);
    }

    /**
     * 删除商品进货信息
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        //根据进货ID查询进货信息
        YwImport ywImport = baseMapper.selectById(id);
        //根据商品ID查询商品信息
        YwGoods goods = ywGoodsMapper.selectById(ywImport.getGoodsid());
        //库存算法  当前库存-进货单数量
        goods.setNumber(goods.getNumber() - ywImport.getNumber());
        ywGoodsMapper.updateById(goods);
        //更新商品的数量
        return super.removeById(id);
    }

}
