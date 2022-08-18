package com.alan.mall.portal.user.controller;

import com.alan.mall.common.core.result.RespResult;
import com.alan.mall.common.core.utils.CookieUtil;
import com.alan.mall.service.user.sdk.annotation.Anoymous;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import com.alan.mall.service.user.api.dto.KaptchaCodeRequest;
import com.alan.mall.service.user.api.dto.KaptchaCodeResponse;
import com.alan.mall.service.user.api.dto.UserLoginRequest;
import com.alan.mall.service.user.api.dto.UserLoginResponse;
import com.alan.mall.service.user.sdk.globaltokenconfig.TokenGlobalConfig;
import com.alan.mall.service.user.api.manager.IKaptchaService;
import com.alan.mall.service.user.api.manager.IUserLoginService;
import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "mall-login", description = "登录")
public class LoginController {

    @DubboReference(check = false)
    IUserLoginService iUserLoginService;

    @DubboReference(check = false)
    IKaptchaService iKaptchaService;

    /**
     * 验证码开关
     */
    @Value(value ="${user.captchaFlag}")
    private boolean captchaFlag;

    @Anoymous
    @PostMapping("/login")
    @Operation(description = "login方法")
    public RespResult<UserLoginResponse> login(@Parameter(description = "登录信息") @RequestBody Map<String,String> map,
                            @Parameter(description = "请求信息") HttpServletRequest request,
                            @Parameter(description = "返回信息") HttpServletResponse response) {
        UserLoginRequest loginRequest=new UserLoginRequest();
        loginRequest.setPassword(map.get("userPwd"));
        loginRequest.setUsername(map.get("userName"));

        if (captchaFlag) {
            KaptchaCodeRequest kaptchaCodeRequest = new KaptchaCodeRequest();
            // 根据kaptcha_uuid 获取对应的value uuid
            String uuid = CookieUtil.getCookieValue(request,"kaptcha_uuid");
            kaptchaCodeRequest.setCode(map.get("captcha"));
            kaptchaCodeRequest.setUuid(uuid);

            KaptchaCodeResponse kaptchaCodeResponse = iKaptchaService.validateKaptcha(kaptchaCodeRequest);

            if (!kaptchaCodeResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
                return RespResult.error(kaptchaCodeResponse.getMsg());
            }
        }

        UserLoginResponse userLoginResponse=iUserLoginService.login(loginRequest);

        if(userLoginResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            Cookie cookie = CookieUtil.genCookie(TokenGlobalConfig.ACCESS_TOKEN, userLoginResponse.getToken(),"/",24*60*60);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return RespResult.ok(userLoginResponse);
        }
        return RespResult.error(userLoginResponse.getMsg());
    }

    @GetMapping("/login")
    @Operation(description = "check-login")
    public RespResult<Object> checkLogin(HttpServletRequest request){
        String userInfo=(String)request.getAttribute(TokenGlobalConfig.USER_INFO_KEY);
        Object object= JSON.parse(userInfo);
        return RespResult.ok(object);
    }

    @GetMapping("/loginOut")
    @Operation(description = "login-out")
    public RespResult<Object> loginOut(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (null!=cookies) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(TokenGlobalConfig.ACCESS_TOKEN)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    response.addCookie(cookie); //覆盖原来的token
                }
            }
        }
        return RespResult.ok();
    }

}
