package com.future.yw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.yw.model.entity.YwProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Repository
public interface YwProviderMapper extends BaseMapper<YwProvider> {

    /**
     * 根据供应商id删除商品信息
     * @param id
     */
    void deleteGoodsByProviderId(@Param("pid") Integer id);

    /**
     * 根据供应商id删除商品进货信息
     * @param id
     */
    void deleteInportByProviderId(@Param("pid") Integer id);

    /**
     * 根据供应商id删除商品退货信息
     * @param id
     */
    void deleteOutPortByProviderId(@Param("pid") Integer id);


}
