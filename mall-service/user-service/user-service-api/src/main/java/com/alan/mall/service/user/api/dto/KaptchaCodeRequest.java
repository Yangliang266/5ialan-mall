package com.alan.mall.service.user.api.dto;

import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Data
public class KaptchaCodeRequest extends AbstractRequest {
    // 为什么需要uuid 是否一个user 对应一个验证码
    private String uuid = UUID.randomUUID().toString();

    private String code;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(uuid)||StringUtils.isBlank(code)){
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}

