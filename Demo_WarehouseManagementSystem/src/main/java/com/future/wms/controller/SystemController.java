package com.future.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author evanliu
 * @create 2021-03-24 22:15
 */
@Controller
@RequestMapping("/")
public class SystemController {

    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "system/index/login";
    }

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "system/index/FrameAll";
    }

    /**
     * 跳转到工作台
     * @return
     */
    @RequestMapping("/toDeskManager")
    public String toDeskManager() {
        return "system/index/DeskManager";
    }
}
