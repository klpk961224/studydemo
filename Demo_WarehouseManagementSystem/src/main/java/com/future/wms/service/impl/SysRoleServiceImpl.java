package com.future.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.wms.mapper.SysRoleMapper;
import com.future.wms.model.entity.SysRole;
import com.future.wms.service.ISysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    /**
     * 删除角色时同时删除sys_user_role表和sys_role_permission表中的数据
     * @param id    角色id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        //根据角色ID删除sys_role_permission表中的数据
        this.getBaseMapper().deleteRolePermissionByRid(id);
        //根据角色ID删除sys_user_role表中的数据
        this.getBaseMapper().deleteUserRoleByRid(id);
        return super.removeById(id);
    }

    /**
     * 根据角色ID查询当前角色拥有的菜单ID和权限ID
     * @param roleId    角色id
     * @return
     */
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId) {
        return this.getBaseMapper().queryRolePermissionIdsByRid(roleId);
    }

    /**
     * 根据角色ID查询当前角色拥有的菜单ID和权限ID的父节点ID
     * @param roleId    角色id
     * @return
     */
    @Override
    public List<Integer> queryRolePermissionFatherNodeIdsByRid(Integer roleId) {
        return this.getBaseMapper().queryRolePermissionFatherNodeIdsByRid(roleId);
    }

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param ids
     */
    @Override
    public void saveRolePermission(Integer rid, Integer[] ids) {
        SysRoleMapper roleMapper = this.getBaseMapper();
        //根据rid删除sys_role_permission
        roleMapper.deleteRolePermissionByRid(rid);
        if (ids != null && ids.length > 0) {
            for (Integer pid : ids) {
                roleMapper.saveRolePermission(rid, pid);
            }
        }
    }

    /**
     * 查询当前用户拥有的角色ID集合
     * @param id    角色id
     * @return
     */
    @Override
    public List<Integer> queryUserRoleIdsByUid(Integer id) {
        return getBaseMapper().queryUserRoleIdsByUid(id);
    }
}
