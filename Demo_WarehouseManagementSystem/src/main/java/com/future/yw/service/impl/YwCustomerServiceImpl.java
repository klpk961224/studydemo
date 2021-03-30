package com.future.yw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.yw.mapper.YwCustomerMapper;
import com.future.yw.mapper.YwGoodsMapper;
import com.future.yw.model.entity.YwCustomer;
import com.future.yw.service.IYwCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Service
public class YwCustomerServiceImpl extends ServiceImpl<YwCustomerMapper, YwCustomer> implements IYwCustomerService {

    @Autowired
    private YwGoodsMapper ywGoodsMapper;

    @Override
    public boolean save(YwCustomer entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(YwCustomer entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public YwCustomer getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    /**
     * 根据客户id删除客户
     * @param id    客户id
     */
    @Override
    public void deleteCustomerById(Integer id) {
        //根据客户id删除商品销售
        ywGoodsMapper.deleteSaleByCustomerId(id);
        //根据客户id删除商品销售退货
        ywGoodsMapper.deleteSaleBackByCustomerId(id);
        this.removeById(id);
    }

}
