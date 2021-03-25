package com.future.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.future.wms.common.*;
import com.future.wms.model.entity.SysPermission;
import com.future.wms.model.entity.SysUser;
import com.future.wms.model.vo.SysPermissionVo;
import com.future.wms.service.ISysPermissionService;
import com.future.wms.service.ISysRoleService;
import com.future.wms.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <p>
 *  菜单管理前端控制器
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private ISysPermissionService sysPermissionService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;

    @RequestMapping("/loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(SysPermissionVo permissionVo) {
        // 查询所有菜单
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        // 设置只能查询菜单且可用的
        queryWrapper.eq("type", Constast.TYPE_MENU);
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        //针对用户角色，展示不同的菜单显示权限
        SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
        List<SysPermission> list = null;
        if (Constast.USER_TYPE_SUPER.equals(user.getType())) {
            list = sysPermissionService.list(queryWrapper);
        } else {
            //用户类型为 普通用户
            //根据用户ID+角色+权限去查询
            Integer userId = user.getId();
            //1.根据用户ID查询角色
            List<Integer> currentUserRoleIds = sysRoleService.queryUserRoleIdsByUid(userId);
            //2.根据角色ID查询菜单ID和权限ID
            //使用set去重
            Set<Integer> pids = new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                //根据角色ID查询菜单ID和权限ID
                List<Integer> permissionIds = sysRoleService.queryRolePermissionIdsByRid(rid);
                //将菜单ID和权限ID放入Set中去重
                pids.addAll(permissionIds);
            }
            //3.根据角色ID查询权限
            if (pids.size() > 0) {
                queryWrapper.in("id", pids);
                list = sysPermissionService.list(queryWrapper);
            } else {
                list = new ArrayList<>();
            }

        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (SysPermission permission : list) {
            Integer id = permission.getId();
            Integer pid = permission.getPid();
            String title = permission.getTitle();
            String icon = permission.getIcon();
            String href = permission.getHref();
            Boolean spread = permission.getOpen().equals(Constast.OPEN_TRUE) ? true : false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
        }

        //构造层级关系
        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes, 1);
        return new DataGridView(list2);


    }
}

