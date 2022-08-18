package com.alan.mall.service.user.api.manager;


import com.alan.mall.service.user.api.dto.KaptchaCodeRequest;
import com.alan.mall.service.user.api.dto.KaptchaCodeResponse;

public interface IKaptchaService {
    /**
     * 获取验证码
     * @param kaptchaCodeRequest
     * @return
     */
    KaptchaCodeResponse getKaptchaCode(KaptchaCodeRequest kaptchaCodeRequest);

    /**
     * 验证验证码
     * @param kaptchaCodeRequest
     * @return
     */
    KaptchaCodeResponse validateKaptcha(KaptchaCodeRequest kaptchaCodeRequest);
}
