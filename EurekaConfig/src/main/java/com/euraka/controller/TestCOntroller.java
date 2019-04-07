package com.euraka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/19.
 */
@RestController
@Slf4j
public class TestCOntroller {


    @RequestMapping("/hello")
    public String test (){
        log.info("test调用 {}");
        return "你好";
    }


}
