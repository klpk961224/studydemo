package com.future.yw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.future.wms.common.Constast;
import com.future.wms.common.DataGridView;
import com.future.wms.common.ResultObj;
import com.future.yw.model.entity.YwProvider;
import com.future.yw.model.vo.YwProviderVo;
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
@RequestMapping("yw/provider")
public class YwProviderController {

    @Autowired
    private IYwProviderService iYwProviderService;

    /**
     * 查询所有的供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("/loadAllProvider")
    public DataGridView loadAllProvider(YwProviderVo providerVo) {
        //1.声明一个分页page对象
        IPage<YwProvider> page = new Page(providerVo.getPage(), providerVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<YwProvider> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getProvidername()), "providername", providerVo.getProvidername());
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getConnectionperson()), "connectionperson", providerVo.getConnectionperson());
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getPhone()), "phone", providerVo.getPhone());
        iYwProviderService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加一个供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("/addProvider")
    public ResultObj addProvider(YwProviderVo providerVo) {
        try {
            iYwProviderService.save(providerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("/updateProvider")
    public ResultObj updateProvider(YwProviderVo providerVo) {
        try {
            iYwProviderService.updateById(providerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除一个供应商
     * @param id
     * @return
     */
    @RequestMapping("/deleteProvider")
    public ResultObj deleteProvider(Integer id) {
        try {
            iYwProviderService.deleteProviderById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 加载所有可用的供应商
     * @return
     */
    @RequestMapping("/loadAllProviderForSelect")
    public DataGridView loadAllProviderForSelect() {
        QueryWrapper<YwProvider> queryWrapper = new QueryWrapper<YwProvider>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<YwProvider> list = iYwProviderService.list(queryWrapper);
        return new DataGridView(list);
    }

}

