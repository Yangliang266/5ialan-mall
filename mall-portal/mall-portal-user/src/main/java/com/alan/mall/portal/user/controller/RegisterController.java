package com.alan.mall.portal.user.controller;

import com.alan.mall.common.core.result.RespResult;
import com.alan.mall.common.core.utils.CookieUtil;
import com.alan.mall.service.user.sdk.annotation.Anoymous;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import com.alan.mall.service.user.api.dto.KaptchaCodeRequest;
import com.alan.mall.service.user.api.dto.KaptchaCodeResponse;
import com.alan.mall.service.user.api.dto.UserRegisterRequest;
import com.alan.mall.service.user.api.dto.UserRegisterResponse;
import com.alan.mall.service.user.api.manager.IKaptchaService;
import com.alan.mall.service.user.api.manager.IUserRegisterService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class RegisterController {
    @DubboReference(check = false)
    IKaptchaService kaptchaService;

    @DubboReference(check = false)
    IUserRegisterService iUserRegisterService;

    @PostMapping("/register")
    @Anoymous
    public RespResult register(@RequestBody Map<String,String> map, HttpServletRequest request) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        // check username passworld
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName(map.get("userName"));
        userRegisterRequest.setUserPwd(map.get("userPwd"));
        userRegisterRequest.setEmail(map.get("email"));
        userRegisterRequest.requestCheck();

        // check imgCode
        KaptchaCodeRequest kaptchaCodeRequest = new KaptchaCodeRequest();
        String uuid = CookieUtil.getCookieValue(request, "kaptcha_uuid");
        kaptchaCodeRequest.setUuid(uuid);
        kaptchaCodeRequest.setCode(map.get("captcha"));
        KaptchaCodeResponse kaptchaCodeResponse = kaptchaService.validateKaptcha(kaptchaCodeRequest);

        // register user
        if (kaptchaCodeResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            // imgCode验证通过
            userRegisterResponse = iUserRegisterService.register(userRegisterRequest);

            if(userRegisterResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
                return RespResult.ok();
            }
            // 返回用户注册错误
            return RespResult.error(userRegisterResponse.getMsg());
        }

        // 返回code验证错误
        return RespResult.error(kaptchaCodeResponse.getMsg());
    }
}
