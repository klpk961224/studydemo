package com.future.report.controller;

import com.future.report.model.vo.SalesInfoForReportBarVo;
import com.future.yw.mapper.YwSalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author evanliu
 * @create 2021-04-02 19:33
 */

@RestController
@RequestMapping("report/echarts")
public class ReportSalesController {

    @Autowired
    private YwSalesMapper ywSalesMapper;

    @RequestMapping("/loadAllSalesInfoForReport")
    public Map<String, Object> loadAllSalesInfoForReport1() {
        // 定义返回的map
        Map<String, Object> map = new HashMap<>(16);
        // 定义第一个option的相关数据
        List<String> option1_xAxis_data = new ArrayList<>();
        List<Integer> option1_series_data = new ArrayList<>();
        // 定义第二个option的相关数据
        List<Map<String, Object>> option2_series_data = new ArrayList<>();

        List<SalesInfoForReportBarVo> infolist = ywSalesMapper.findSalesInfoForReport1BarVo();

        for (SalesInfoForReportBarVo info : infolist) {
            // 遍历塞入option1的值
            option1_xAxis_data.add(infolist.indexOf(info), info.getGoodsname() + "-" + info.getSize());
            option1_series_data.add(infolist.indexOf(info), info.getTotalnum());
            // 遍历塞入option2的值
            Map<String, Object> option2_map = new HashMap<>(16);
            option2_map.put("name", info.getGoodsname() + "-" + info.getSize());
            option2_map.put("value", info.getTotalnum());
            option2_series_data.add(option2_map);
        }
        map.put("option1_xAxis_data", option1_xAxis_data);
        map.put("option1_series_data", option1_series_data);
        map.put("option2_series_data", option2_series_data);
        return map;
    }
}
