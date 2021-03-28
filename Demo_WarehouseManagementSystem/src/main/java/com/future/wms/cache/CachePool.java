package com.future.wms.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.future.wms.mapper.*;
import com.future.wms.model.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author evanliu
 * @create 2021-03-27 18:15
 */
public class CachePool {

    /**
     * 所有的缓存数据放到这个CACHE_CONTAINER  类似于redis
     */
    public static volatile Map<String, Object> CACHE_CONTAINER = new HashMap<>();

    /**
     * 根据KEY删除缓存
     * @param key   键
     */
    public static void removeCacheByKey(String key) {
        if (CACHE_CONTAINER.containsKey(key)) {
            CACHE_CONTAINER.remove(key);
        }
    }

    /**
     * 清空所有缓存
     */
    public static void removeAll() {
        CACHE_CONTAINER.clear();
    }

    /**
     * 同步缓存
     */
    public static void syncData() {
        //同步部门数据
        SysDeptMapper deptMapper = SpringUtil.getBean(SysDeptMapper.class);
        List<SysDept> deptList = deptMapper.selectList(null);
        for (SysDept dept : deptList) {
            CACHE_CONTAINER.put("dept:" + dept.getId(), dept);
        }
        //同步用户数据
        SysUserMapper userMapper = SpringUtil.getBean(SysUserMapper.class);
        List<SysUser> userList = userMapper.selectList(null);
        for (SysUser user : userList) {
            CACHE_CONTAINER.put("user:" + user.getId(), user);
        }

        //同步客户数据
        YwCustomerMapper customerMapper = SpringUtil.getBean(YwCustomerMapper.class);
        List<YwCustomer> customerList = customerMapper.selectList(null);
        for (YwCustomer customer : customerList) {
            CACHE_CONTAINER.put("customer:" + customer.getId(), customer);
        }

        //同步供应商数据
        YwProviderMapper providerMapper = SpringUtil.getBean(YwProviderMapper.class);
        List<YwProvider> providerList = providerMapper.selectList(null);
        for (YwProvider provider : providerList) {
            CACHE_CONTAINER.put("provider:" + provider.getId(), provider);
        }

        //同步商品数据
        YwGoodsMapper goodsMapper = SpringUtil.getBean(YwGoodsMapper.class);
        List<YwGoods> goodsList = goodsMapper.selectList(null);
        for (YwGoods goods : goodsList) {
            CACHE_CONTAINER.put("goods:" + goods.getId(), goods);
        }

    }


}
