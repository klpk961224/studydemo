package com.future.yw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.AppFileUtils;
import com.future.wms.common.Constast;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.yw.model.entity.YwGoods;
import com.future.yw.model.entity.YwProvider;
import com.future.yw.model.vo.YwGoodsVo;
import com.future.yw.service.IYwGoodsService;
import com.future.yw.service.IYwProviderService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("yw/goods")
public class YwGoodsController {

    @Autowired
    private IYwGoodsService iYwGoodsService;

    @Autowired
    private IYwProviderService iYwProviderService;

    /**
     * 查询商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("/loadAllGoods")
    public DataGridView loadAllGoods(YwGoodsVo goodsVo) {
        IPage<YwGoods> page = new Page<YwGoods>(goodsVo.getPage(), goodsVo.getLimit());
        QueryWrapper<YwGoods> queryWrapper = new QueryWrapper<YwGoods>();
        queryWrapper.eq(goodsVo.getProviderid() != null && goodsVo.getProviderid() != 0, "providerid", goodsVo.getProviderid());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()), "goodsname", goodsVo.getGoodsname());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()), "productcode", goodsVo.getProductcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()), "promitcode", goodsVo.getPromitcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()), "description", goodsVo.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getSize()), "size", goodsVo.getSize());

        queryWrapper.orderByDesc("id");
        iYwGoodsService.page(page, queryWrapper);
        List<YwGoods> records = page.getRecords();
        for (YwGoods goods : records) {
            YwProvider provider = iYwProviderService.getById(goods.getProviderid());
            if (null != provider) {
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("/addGoods")
    public ResultObj addGoods(YwGoodsVo goodsVo) {
        try {
            System.out.println("====================================");
            System.out.println(goodsVo.getGoodsimg());
            if (goodsVo.getGoodsimg() != null && goodsVo.getGoodsimg().endsWith("_temp")) {
                String newName = AppFileUtils.renameFile(goodsVo.getGoodsimg());
                goodsVo.setGoodsimg(newName);
            }
            iYwGoodsService.save(goodsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("/updateGoods")
    public ResultObj updateGoods(YwGoodsVo goodsVo) {
        try {
            //商品图片不是默认图片
            if (!(goodsVo.getGoodsimg() != null && goodsVo.getGoodsimg().equals(Constast.DEFAULT_IMG_GOODS))) {

                if (goodsVo.getGoodsimg().endsWith("_temp")) {
                    String newName = AppFileUtils.renameFile(goodsVo.getGoodsimg());
                    goodsVo.setGoodsimg(newName);
                    //删除原先的图片
                    String oldPath = iYwGoodsService.getById(goodsVo.getId()).getGoodsimg();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            iYwGoodsService.updateById(goodsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除商品
     * @param id 商品id
     * @return
     */
    @RequestMapping("/deleteGoods")
    public ResultObj deleteGoods(Integer id, String goodsimg) {
        try {
            //删除商品的图片
            AppFileUtils.removeFileByPath(goodsimg);
//            goodsService.removeById(id);
            iYwGoodsService.deleteGoodsById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有可用的商品
     * @return
     */
    @RequestMapping("/loadAllGoodsForSelect")
    public DataGridView loadAllGoodsForSelect() {
        QueryWrapper<YwGoods> queryWrapper = new QueryWrapper<YwGoods>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<YwGoods> list = iYwGoodsService.list(queryWrapper);
        for (YwGoods goods : list) {
            YwProvider provider = iYwProviderService.getById(goods.getProviderid());
            if (null != provider) {
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }

    /**
     * 根据供应商ID查询商品信息
     * @param providerid    供应商ID
     * @return
     */
    @RequestMapping("/loadGoodsByProviderId")
    public DataGridView loadGoodsByProviderId(Integer providerid) {
        QueryWrapper<YwGoods> queryWrapper = new QueryWrapper<YwGoods>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        queryWrapper.eq(providerid != null, "providerid", providerid);
        List<YwGoods> list = iYwGoodsService.list(queryWrapper);
        for (YwGoods goods : list) {
            YwProvider provider = iYwProviderService.getById(goods.getProviderid());
            if (null != provider) {
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }

    @RequestMapping("/loadAllWarningGoods")
    public DataGridView loadAllWarningGoods() {
        List<YwGoods> goods = iYwGoodsService.loadAllWarning();
        return new DataGridView((long) goods.size(), goods);
    }


}

