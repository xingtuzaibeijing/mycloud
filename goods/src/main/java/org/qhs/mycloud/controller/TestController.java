package org.qhs.mycloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    private String serverPort;


    @GetMapping("testTimeOut")
    public String testTimeOut (){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "进来了"+serverPort;
    }

    @GetMapping("test")
    public String test (){
        return "进来了"+serverPort;
    }
}
