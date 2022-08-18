package com.alan.mall.service.user.provider.service;

import com.alan.mall.service.user.api.manager.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(loadbalance = "random")
public class HelloService implements IHelloService {

    @Override
    public String say(String msg) {
        return "Hello,"+msg+", I' Dubbo Service";
    }
}
