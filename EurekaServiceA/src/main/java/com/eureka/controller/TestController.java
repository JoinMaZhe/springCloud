package com.eureka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/19.
 */
@RestController
@Slf4j
@RefreshScope
public class TestController {
    @Value("${test}")
    private String test;

    @RequestMapping("/hello")
    public String test (){
        log.info("test调用 {}",test);
        return "成功："+test;
    }


}
