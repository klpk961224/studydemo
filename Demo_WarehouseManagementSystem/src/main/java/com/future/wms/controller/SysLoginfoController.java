package com.future.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.wms.model.entity.SysLoginfo;
import com.future.wms.model.vo.LoginfoVo;
import com.future.wms.service.ISysLoginfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author evanliu
 * @create 2021-03-24 23:24
 */
@RestController
@RequestMapping("/loginfo")
public class SysLoginfoController {

    @Autowired
    private ISysLoginfoService sysLoginfoService;

    /**
     * @Description [查询所有登陆日志的信息]
     * @Author evanliu
     * @Date 2021-03-25 23:37
     * @param: loginfoVo ->
     * @return com.future.wms.common.DataGridView
     **/
    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {
        IPage<SysLoginfo> page = new Page<SysLoginfo>(loginfoVo.getPage(), loginfoVo.getLimit());
        QueryWrapper<SysLoginfo> queryWrapper = new QueryWrapper<>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()), "loginname", loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip", loginfoVo.getLoginip());
        //数据库中登陆时间要大于用户输入的开始时间且小于用户登陆的结束时间
        queryWrapper.ge(loginfoVo.getStartTime() != null, "logintime", loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime() != null, "logintime", loginfoVo.getEndTime());
        //根据登陆时间进行降序排序
        queryWrapper.orderByDesc("logintime");
        this.sysLoginfoService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * @Description [删除单条日志]
     * @Author evanliu
     * @Date 2021-03-25 23:39
     * @param: id ->
     * @return com.future.wms.common.ResultObj
     **/
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id) {
        try {
            this.sysLoginfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * @Description [批量删除]
     * @Author evanliu
     * @Date 2021-03-25 23:39
     * @param: loginfoVo ->
     * @return com.future.wms.common.ResultObj
     **/
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(LoginfoVo loginfoVo) {
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : loginfoVo.getIds()) {
                idList.add(id);
            }
            this.sysLoginfoService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
