package com.future.wms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evanliu
 * @create 2021-03-25 17:03
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/sayhello")
    public String sayHello(){
        return "hello!";
    }
}
