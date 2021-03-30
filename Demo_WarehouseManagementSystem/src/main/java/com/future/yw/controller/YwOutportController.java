package com.future.yw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwOutport;
import com.future.yw.model.entity.YwProvider;
import com.future.yw.model.vo.YwOutportVo;
import com.future.yw.service.IYwGoodsService;
import com.future.yw.service.IYwOutportService;
import com.future.yw.service.IYwProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("yw/outport")
public class YwOutportController {

    @Autowired
    private IYwOutportService iYwOutportService;

    @Autowired
    private IYwProviderService iYwProviderService;

    @Autowired
    private IYwGoodsService iYwGoodsService;

    /**
     * 添加退货信息
     * @param id    进货单ID
     * @param number    退货数量
     * @param remark    备注
     * @return
     */
    @RequestMapping("/addOutport")
    public ResultObj addOutport(Integer id, Integer number, String remark) {
        try {
            iYwOutportService.addOutport(id, number, remark);
            return ResultObj.BACKINPORT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.BACKINPORT_ERROR;
        }
    }

    /**t
     * 查询商品退货
     * @param outportVo
     * @return
     */
    @RequestMapping("/loadAllOutport")
    public DataGridView loadAllOuport(YwOutportVo outportVo) {
        IPage<YwOutport> page = new Page<YwOutport>(outportVo.getPage(), outportVo.getLimit());
        QueryWrapper<YwOutport> queryWrapper = new QueryWrapper<YwOutport>();
        //对供应商进行查询
        queryWrapper.eq(outportVo.getProviderid() != null && outportVo.getProviderid() != 0, "providerid", outportVo.getProviderid());
        //对商品进行查询
        queryWrapper.eq(outportVo.getGoodsid() != null && outportVo.getGoodsid() != 0, "goodsid", outportVo.getGoodsid());
        //对时间进行查询要求大于开始时间小于结束时间
        queryWrapper.ge(outportVo.getStartTime() != null, "outputtime", outportVo.getStartTime());
        queryWrapper.le(outportVo.getEndTime() != null, "outputtime", outportVo.getEndTime());
        //通过进货时间对商品进行排序
        queryWrapper.orderByDesc("outputtime");
        IPage<YwOutport> page1 = iYwOutportService.page(page, queryWrapper);
        List<YwOutport> records = page1.getRecords();
        for (YwOutport ouport : records) {
            YwProvider provider = iYwProviderService.getById(ouport.getProviderid());
            if (provider != null) {
                //设置供应商姓名
                ouport.setProvidername(provider.getProvidername());
            }
            YwGoods goods = iYwGoodsService.getById(ouport.getGoodsid());
            if (goods != null) {
                //设置商品名称
                ouport.setGoodsname(goods.getGoodsname());
                //设置商品规格
                ouport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page1.getTotal(), page1.getRecords());
    }

    /**
     * 删除退货信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteOutport")
    public ResultObj deleteOutport(Integer id) {
        try {
            iYwOutportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

