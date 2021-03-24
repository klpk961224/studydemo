package com.future.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author evanliu
 * @create 2021-03-24 22:15
 */
@Controller
@RequestMapping("sys")
public class SystemController {

    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/index/login";
    }
}
