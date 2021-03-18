package com.future.community.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/sayhello")
    public String SayHello() {
        return "Hello! Welcome!";
    }
}
