package com.eureka.service;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/20.
 */
@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "request error";
    }
}
