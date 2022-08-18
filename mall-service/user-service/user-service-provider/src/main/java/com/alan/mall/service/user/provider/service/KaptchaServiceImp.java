package com.alan.mall.service.user.provider.service;

import com.alan.mall.common.tool.redis.RedissonWrapperClient;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import com.alan.mall.service.user.api.dto.KaptchaCodeRequest;
import com.alan.mall.service.user.api.dto.KaptchaCodeResponse;
import com.alan.mall.service.user.api.manager.IKaptchaService;
import com.alan.mall.service.user.provider.dal.entitys.ImageResult;
import com.alan.mall.service.user.provider.utils.ExceptionProcessorUtils;
import com.alan.mall.service.user.provider.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;


@DubboService
@Slf4j
public class KaptchaServiceImp implements IKaptchaService {
    @Autowired
    RedissonWrapperClient redissonClient;

    // 作为存储到redis中 kaptcha的key
    private final String KAPTCHA_UUID="kaptcha_uuid";


    @Override
    @Cacheable(value = KAPTCHA_UUID,key = "#kaptchaCodeRequest.uuid")
    public KaptchaCodeResponse getKaptchaCode(KaptchaCodeRequest kaptchaCodeRequest) {
        log.info("Begin: KaptchaServiceImp.getKaptchaCode.request: " + kaptchaCodeRequest);
        KaptchaCodeResponse kaptchaCodeResponse = new KaptchaCodeResponse();
        try {
            // 1 获取图片验证码
            ImageResult capText = VerifyCodeUtils.VerifyCode(140, 43, 4);
            kaptchaCodeResponse.setImageCode(capText.getImg());
            kaptchaCodeResponse.setUuid(kaptchaCodeRequest.getUuid());
            kaptchaCodeResponse.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            kaptchaCodeResponse.setCode(SysRetCodeConstants.SUCCESS.getCode());
        } catch (Exception e) {
            log.error("Error: KaptchaServiceImpl.getKaptchaCode.Exception: "+e);
            ExceptionProcessorUtils.wrapperHandlerException(kaptchaCodeResponse,e);
        }
        log.info("End: KaptchaServiceImp.getKaptchaCode.response: " + kaptchaCodeResponse);
        return kaptchaCodeResponse;
    }

    @Override
    @Cacheable(value = KAPTCHA_UUID,key = "#kaptchaCodeRequest.uuid")
    public KaptchaCodeResponse validateKaptcha(KaptchaCodeRequest kaptchaCodeRequest) {
        log.info("Begin: KaptchaServiceImp.validateKaptcha.request: " + kaptchaCodeRequest);
        kaptchaCodeRequest.requestCheck();
        KaptchaCodeResponse kaptchaCodeResponse = new KaptchaCodeResponse();
        try {
            kaptchaCodeResponse.setCode(SysRetCodeConstants.KAPTCHA_CODE_ERROR.getCode());
            kaptchaCodeResponse.setMsg(SysRetCodeConstants.KAPTCHA_CODE_ERROR.getMessage());
        } catch (Exception e) {
            log.error("Error: KaptchaServiceImpl.validateKaptchaCode.Exception: "+e);
            ExceptionProcessorUtils.wrapperHandlerException(kaptchaCodeResponse,e);
        }
        log.info("End: KaptchaServiceImp.validateKaptcha.response: " + kaptchaCodeResponse);
        return kaptchaCodeResponse;
    }
}
