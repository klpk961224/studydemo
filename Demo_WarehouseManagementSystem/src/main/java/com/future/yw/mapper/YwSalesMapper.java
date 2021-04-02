package com.future.yw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.report.model.vo.SalesInfoForReportBarVo;
import com.future.yw.model.entity.YwSales;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evanliu-
 * @since 2021-03-24
 */
@Repository
public interface YwSalesMapper extends BaseMapper<YwSales> {

    @Select("select ys.goodsid,yg.goodsname,yg.size,SUM(ys.number) as totalnum,SUM(ys.number*ys.saleprice) as totalmoney from yw_sales ys, yw_goods yg where ys.goodsid = yg.id GROUP BY goodsid ")
    public List<SalesInfoForReportBarVo> findSalesInfoForReport1BarVo();
}
