package com.future.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.Constast;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.wms.common.TreeNode;
import com.future.wms.model.entity.SysPermission;
import com.future.wms.model.entity.SysRole;
import com.future.wms.model.vo.SysRoleVo;
import com.future.wms.service.ISysPermissionService;
import com.future.wms.service.ISysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysPermissionService sysPermissionService;

    /**
     * 查询所有角色
     * @param roleVo
     * @return
     */
    @RequestMapping("/loadAllRole")
    public DataGridView loadAllRole(SysRoleVo roleVo) {
        IPage<SysRole> page = new Page<SysRole>(roleVo.getPage(), roleVo.getLimit());
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getName()), "name", roleVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
        queryWrapper.eq(roleVo.getAvailable() != null, "available", roleVo.getAvailable());
        queryWrapper.ge(roleVo.getStartTime() != null, "createtime", roleVo.getStartTime());
        queryWrapper.le(roleVo.getEndTime() != null, "createtime", roleVo.getEndTime());
        queryWrapper.orderByDesc("createtime");
        sysRoleService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加角色
     * @param roleVo
     * @return
     */
    @RequestMapping("/addRole")
    public ResultObj addRole(SysRoleVo roleVo) {
        try {
            roleVo.setCreatetime(new Date());
            sysRoleService.save(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     * @param roleVo
     * @return
     */
    @RequestMapping("/updateRole")
    public ResultObj updateRole(SysRoleVo roleVo) {
        try {
            sysRoleService.updateById(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    public ResultObj deleteRole(Integer id) {
        try {
            this.sysRoleService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据角色ID加载菜单和权限的树的json串
     * @param roleId
     * @return
     */
    @RequestMapping("/initPermissionByRoleId")
    public DataGridView initPermissionByRoleId(Integer roleId) {
        //查询所有可用的菜单和权限
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<SysPermission> allPermissions = sysPermissionService.list(queryWrapper);
        //1.首先根据角色id查询出当前角色所拥有的所有菜单的ID和权限的ID
        List<Integer> currentRolePermissions = sysRoleService.queryRolePermissionIdsByRid(roleId);
        //2.根据查询出来的菜单ID和权限ID，再查询出菜单的数据和权限的数据
        List<SysPermission> currentPermissions = null;
        //如果根据角色id查询出来了菜单ID或权限ID，就去查询
        if (currentRolePermissions.size() > 0) {
            queryWrapper.in("id", currentRolePermissions);
            currentPermissions = sysPermissionService.list(queryWrapper);
        } else {
            currentPermissions = new ArrayList<>();
        }
        //构造List<TreeNode>
        List<TreeNode> nodes = new ArrayList<>();
        for (SysPermission allPermission : allPermissions) {
            String checkArr = "0";
            for (SysPermission currentPermission : currentPermissions) {
                if (allPermission.getId().equals(currentPermission.getId())) {
                    checkArr = "1";
                    break;
                }
            }
            Boolean spread = (allPermission.getOpen() == null || allPermission.getOpen() == 1) ? true : false;
            nodes.add(new TreeNode(allPermission.getId(), allPermission.getPid(), allPermission.getTitle(), spread, checkArr));
        }
        return new DataGridView(nodes);
    }

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param ids
     * @return
     */
    @RequestMapping("/saveRolePermission")
    public ResultObj saveRolePermission(Integer rid, Integer[] ids) {
        try {
            sysRoleService.saveRolePermission(rid, ids);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }

    }

}
