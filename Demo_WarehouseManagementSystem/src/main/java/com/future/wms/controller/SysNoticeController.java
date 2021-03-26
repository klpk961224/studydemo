package com.future.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.wms.common.WebUtils;
import com.future.wms.model.entity.SysNotice;
import com.future.wms.model.entity.SysUser;
import com.future.wms.model.vo.SysNoticeVo;
import com.future.wms.service.ISysNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/notice")
public class SysNoticeController {

    @Autowired
    private ISysNoticeService sysNoticeService;

    /**
     * 公告的查询
     * @param noticeVo
     * @return
     */
    @RequestMapping("loadAllNotice")
    public DataGridView loadAllNotice(SysNoticeVo noticeVo) {
        IPage<SysNotice> page = new Page<SysNotice>(noticeVo.getPage(), noticeVo.getLimit());
        QueryWrapper<SysNotice> queryWrapper = new QueryWrapper<SysNotice>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()), "title", noticeVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()), "opername", noticeVo.getOpername());
        //公告创建时间应该大于搜索开始时间小于搜索结束时间
        queryWrapper.ge(noticeVo.getStartTime() != null, "createtime", noticeVo.getStartTime());
        queryWrapper.le(noticeVo.getEndTime() != null, "createtime", noticeVo.getEndTime());
        //根据公告创建时间进行排序
        queryWrapper.orderByDesc("createtime");
        this.sysNoticeService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 根据公告ID查询一条公告
     * @param id    公告ID
     * @return
     */
    @RequestMapping("loadNoticeById")
    public DataGridView loadNoticeById(Integer id) {
        SysNotice notice = this.sysNoticeService.getById(id);
        return new DataGridView(notice);
    }

    /**
     * 添加公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("addNotice")
    public ResultObj addNotice(SysNoticeVo noticeVo) {
        try {
            noticeVo.setCreatetime(new Date());
            SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
            noticeVo.setOpername(user.getName());
            this.sysNoticeService.save(noticeVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(SysNoticeVo noticeVo) {
        try {
            this.sysNoticeService.updateById(noticeVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("deleteNotice")
    public ResultObj deleteNotice(SysNoticeVo noticeVo) {
        try {
            this.sysNoticeService.removeById(noticeVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("batchDeleteNotice")
    public ResultObj batchDeleteNotice(SysNoticeVo noticeVo) {
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : noticeVo.getIds()) {
                idList.add(id);
            }
            this.sysNoticeService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}
