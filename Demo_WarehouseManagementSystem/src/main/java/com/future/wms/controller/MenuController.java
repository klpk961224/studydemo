package com.future.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.*;
import com.future.wms.model.entity.SysPermission;
import com.future.wms.model.entity.SysUser;
import com.future.wms.model.vo.SysPermissionVo;
import com.future.wms.service.ISysPermissionService;
import com.future.wms.service.ISysRoleService;
import com.future.wms.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


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
    /************************菜单管理*********************************/

    /**
     * 加载菜单左边的菜单树
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(SysPermissionVo permissionVo) {

        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constast.TYPE_MENU);
        //查询出所有的菜单，存放进list中
        List<SysPermission> list = sysPermissionService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        //将菜单放入treeNodes中，组装成json
        for (SysPermission menu : list) {
            Boolean open = menu.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), open));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询所有菜单数据
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(SysPermissionVo permissionVo) {
        IPage<SysPermission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        //进行模糊查询
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(permissionVo.getId() != null, "id", permissionVo.getId()).or().eq(permissionVo.getId() != null, "pid", permissionVo.getId());
        //只能查询菜单
        queryWrapper.eq("type", Constast.TYPE_MENU);
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        //进行查询
        sysPermissionService.page(page, queryWrapper);
        //返回DataGridView
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(SysPermissionVo permissionVo) {
        try {
            //设置添加类型为 menu
            permissionVo.setType(Constast.TYPE_MENU);
            sysPermissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 加载排序码
     * @return
     */
    @RequestMapping("loadMenuMaxOrderNum")
    public Map<String, Object> loadMenuMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<SysPermission> page = new Page<>(1, 1);
        List<SysPermission> list = sysPermissionService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 更新菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(SysPermissionVo permissionVo) {
        try {
            sysPermissionService.updateById(permissionVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 检查当前菜单是否有子菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("checkMenuHasChildrenNode")
    public Map<String, Object> checkMenuHasChildrenNode(SysPermissionVo permissionVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", permissionVo.getId());
        List<SysPermission> list = sysPermissionService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(SysPermissionVo permissionVo) {
        try {
            sysPermissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}

