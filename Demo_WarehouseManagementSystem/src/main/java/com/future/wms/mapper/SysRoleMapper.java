package com.future.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.wms.model.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * @Description [根据角色ID删除sys_role_permission表中的数据]
     * @Author evanliu
     * @Date 2021-03-25 17:58
     * @param: id -> 角色的id
     * @return void
     **/
    void deleteRolePermissionByRid(@Param("pid") Serializable id);

    /**
     * @Description [根据角色ID删除sys_user_role表中的数据]
     * @Author evanliu
     * @Date 2021-03-25 17:59
     * @param: id -> 角色的id
     * @return
     **/
    void deleteUserRoleByRid(@Param("pid") Serializable id);

    /**
     * @Description [根据角色ID查询当前角色拥有的菜单ID和权限ID]
     * @Author evanliu
     * @Date 2021-03-25 17:59
     * @param: roleId ->
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> queryRolePermissionIdsByRid(@Param("roleId") Integer roleId);

    /**
     * @Description [根据角色ID查询当前角色拥有的菜单ID和权限ID]
     * @Author evanliu
     * @Date 2021-03-25 17:59
     * @param: roleId ->
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> queryRolePermissionFatherNodeIdsByRid(@Param("roleId") Integer roleId);

    /**
     * @Description [保存角色和菜单权限之间的关系]
     * @Author evanliu
     * @Date 2021-03-25 18:00
     * @param: rid ->
     * @param: pid ->
     * @return void
     **/
    void saveRolePermission(@Param("rid") Integer rid, @Param("pid") Integer pid);

    /**
     * @Description [根据用户id删除用户角色中间表的数据]
     * @Author evanliu
     * @Date 2021-03-25 18:00
     * @param: id ->
     * @return void
     **/
    void deleteRoleUserByUid(@Param("id") Serializable id);

    /**
     * @Description [查询当前用户拥有的角色ID集合]
     * @Author evanliu
     * @Date 2021-03-25 18:00
     * @param: id ->
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> queryUserRoleIdsByUid(@Param("id") Integer id);

    /**
     * @Description [保存用户和角色的关系]
     * @Author evanliu
     * @Date 2021-03-25 18:01
     * @param: uid -> 用户的ID
     * @param: rid -> 用户拥有的角色的ID的数组
     * @return void
     **/
    void insertUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);

}
