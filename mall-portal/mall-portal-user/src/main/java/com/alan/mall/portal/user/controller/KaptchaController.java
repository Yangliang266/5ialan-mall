package com.alan.mall.portal.user.controller;

import com.alan.mall.common.core.result.RespResult;
import com.alan.mall.common.core.utils.CookieUtil;
import com.alan.mall.service.user.sdk.annotation.Anoymous;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import com.alan.mall.service.user.api.dto.KaptchaCodeRequest;
import com.alan.mall.service.user.api.dto.KaptchaCodeResponse;
import com.alan.mall.service.user.api.manager.IKaptchaService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class KaptchaController {

    @DubboReference(check = false)
    IKaptchaService iKaptchaService;

    @Anoymous
    @GetMapping("/kaptcha")
    public RespResult<String> getKaptchaCode(HttpServletResponse response) {
        KaptchaCodeRequest kaptchaCodeRequest = new KaptchaCodeRequest();

        KaptchaCodeResponse kaptchaCodeResponse = iKaptchaService.getKaptchaCode(kaptchaCodeRequest);

        if(kaptchaCodeResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())){
            Cookie cookie= CookieUtil.genCookie("kaptcha_uuid",kaptchaCodeResponse.getUuid(),"/",60);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return RespResult.ok(kaptchaCodeResponse.getImageCode());
        }
        return RespResult.error(kaptchaCodeResponse.getCode());
    }

    @Anoymous
    @PostMapping("/kaptcha")
    public RespResult<String> validatakaptchaCode(@RequestBody String code, HttpServletRequest httpServletRequest) {
        KaptchaCodeRequest request = new KaptchaCodeRequest();
        // 验证code 是否与redis一致
        String uuid = CookieUtil.getCookieValue(httpServletRequest,"kaptcha_uuid");

        request.setUuid(uuid);
        request.setCode(code);

        KaptchaCodeResponse response = iKaptchaService.getKaptchaCode(request);

        if (response.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            return RespResult.ok();
        }

        return RespResult.error(response.getCode());
    }

}
