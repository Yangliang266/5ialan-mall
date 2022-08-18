package com.alan.mall.portal.user.controller;

import com.alan.mall.service.user.api.manager.ITest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: mathyoung
 * @description:
 */
@RestController
@Tag(name="dubbo测试类")
public class DubboITestController {

    @DubboReference
    ITest testService;

    @GetMapping("/dubbotest/type")
    @Parameter(description = "test")
    public String test(@RequestParam(value = "name")String name) {
        String admin1 = testService.test(name);
        return admin1;
    }
}
