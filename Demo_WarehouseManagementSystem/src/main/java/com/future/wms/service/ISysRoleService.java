package com.future.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.wms.model.entity.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * @Description [根据角色ID查询当前角色拥有的菜单ID和权限ID]
     * @Author evanliu
     * @Date 2021-03-25 17:52
     * @param: roleId ->
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * @Description [保存角色和菜单权限之间的关系]
     * @Author evanliu
     * @Date 2021-03-25 17:53
     * @param: rid ->
     * @param: ids ->
     * @return void
     **/
    void saveRolePermission(Integer rid, Integer[] ids);

    /**
     * @Description [查询当前用户拥有的角色ID集合]
     * @Author evanliu
     * @Date 2021-03-25 17:53
     * @param: id ->
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> queryUserRoleIdsByUid(Integer id);

}
