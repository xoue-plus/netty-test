package com.ne.sne.controller;

import com.ne.sne.component.netty.domain.CustomProtocol;
import com.ne.sne.component.netty.server.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    @Autowired
    private NettyClient demo;
    @GetMapping("/test")
    public String test(){
        demo.sendMessage(new CustomProtocol(1,"客户端发送消息为xxxxxxxxxxxx......"));
        return "success...";
    }
}
