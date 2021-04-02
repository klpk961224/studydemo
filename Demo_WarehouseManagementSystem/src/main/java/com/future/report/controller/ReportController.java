package com.future.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author evanliu
 * @create 2021-04-02 19:26
 */
@Controller
@RequestMapping("report")
public class ReportController {

    /**
     * 跳转到报表
     * @return
     */
    @RequestMapping("/toReport")
    public String toReport() {
        return "yw/echarts/report";
    }
}
