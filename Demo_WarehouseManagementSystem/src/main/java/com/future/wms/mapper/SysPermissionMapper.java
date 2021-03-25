package com.future.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.wms.model.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * @Description [根据权限ID或菜单ID删除sys_role_permission表里面的数据]
     * @Author evanliu
     * @Date 2021-03-25 18:04
     * @param: id ->
     * @return void
     **/
    void deleteRolePermissionByPid(@Param("id") Serializable id);

}
