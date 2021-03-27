package com.future.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.wms.mapper.SysDeptMapper;
import com.future.wms.model.entity.SysDept;
import com.future.wms.service.ISysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Override
    public SysDept getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean update(SysDept entity, Wrapper<SysDept> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateById(SysDept entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean save(SysDept entity) {
        return super.save(entity);
    }


}
