package com.eureka.controller;

import com.eureka.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/20.
 */
@RestController
public class HelloCotroller {

    @Autowired
    public HelloService helloService;

    @RequestMapping(value = "/feign-customer")
    public String helloCustomer() {

        return helloService.hello();
    }

}

