package com.mao.springandvideo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    //测试
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
