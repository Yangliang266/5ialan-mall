package com.alan.mall.portal.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @Auther: mathyoung
 * @description:
 */
@RestController
@Tag(name="test测试类")
public class TestController {

    @GetMapping("/test1/{uid}")
    @Operation(description = "testPathVariable方法")
    public String testPathVariable(@Parameter(description = "testPathVariable方法的uid") @PathVariable long uid) {
        return String.valueOf(uid);
    }

    @GetMapping("/test2")
    @Operation(description = "testRequestParam方法")
    public String testRequestParam(@Parameter(description = "testRequestParam方法的name") @RequestParam(name = "username") String username) {
        return username;
    }
}
