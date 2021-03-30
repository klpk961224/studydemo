package com.future.yw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.wms.common.WebUtils;
import com.future.wms.model.entity.SysUser;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwImport;
import com.future.yw.model.entity.YwProvider;
import com.future.yw.model.vo.YwImportVo;
import com.future.yw.service.IYwGoodsService;
import com.future.yw.service.IYwImportService;
import com.future.yw.service.IYwProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("yw/import")
public class YwImportController {

    @Autowired
    private IYwImportService iYwImportService;

    @Autowired
    private IYwProviderService iYwProviderService;

    @Autowired
    private IYwGoodsService iYwGoodsService;

    /**
     * 查询商品进货
     * @param importVo
     * @return
     */
    @RequestMapping("/loadAllImport")
    public DataGridView loadAllImport(YwImportVo importVo) {
        IPage<YwImport> page = new Page<YwImport>(importVo.getPage(), importVo.getLimit());
        QueryWrapper<YwImport> queryWrapper = new QueryWrapper<YwImport>();
        //对供应商进行查询
        queryWrapper.eq(importVo.getProviderid() != null && importVo.getProviderid() != 0, "providerid", importVo.getProviderid());
        //对商品进行查询
        queryWrapper.eq(importVo.getGoodsid() != null && importVo.getGoodsid() != 0, "goodsid", importVo.getGoodsid());
        //对时间进行查询要求大于开始时间小于结束时间
        queryWrapper.ge(importVo.getStartTime() != null, "importtime", importVo.getStartTime());
        queryWrapper.le(importVo.getEndTime() != null, "importtime", importVo.getEndTime());
        //通过进货时间对商品进行排序
        queryWrapper.orderByDesc("importtime");
        IPage<YwImport> page1 = iYwImportService.page(page, queryWrapper);
        List<YwImport> records = page1.getRecords();
        for (YwImport ywimport : records) {
            YwProvider provider = iYwProviderService.getById(ywimport.getProviderid());
            if (provider != null) {
                //设置供应商姓名
                ywimport.setProvidername(provider.getProvidername());
            }
            YwGoods goods = iYwGoodsService.getById(ywimport.getGoodsid());
            if (goods != null) {
                //设置商品名称
                ywimport.setGoodsname(goods.getGoodsname());
                //设置商品规格
                ywimport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page1.getTotal(), page1.getRecords());
    }


    /**
     * 添加进货商品
     * @param importVo
     * @return
     */
    @RequestMapping("/addImport")
    public ResultObj addImport(YwImportVo importVo) {
        try {
            //获得当前系统用户
            SysUser user = (SysUser) WebUtils.getSession().getAttribute("user");
            //设置操作人
            importVo.setOperateperson(user.getName());
            //设置进货时间
            importVo.setImporttime(new Date());
            iYwImportService.save(importVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新进货商品
     * @param importVo
     * @return
     */
    @RequestMapping("/updateImport")
    public ResultObj updateImport(YwImportVo importVo) {
        try {
            iYwImportService.updateById(importVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    /**
     * 删除进货商品
     * @param id
     * @return
     */
    @RequestMapping("/deleteImport")
    public ResultObj deleteImport(Integer id) {
        try {
            iYwImportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

