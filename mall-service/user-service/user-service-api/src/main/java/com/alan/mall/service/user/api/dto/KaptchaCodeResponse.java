package com.alan.mall.service.user.api.dto;

import com.alan.mall.common.core.result.AbstractResponse;
import lombok.Data;

@Data
public class KaptchaCodeResponse extends AbstractResponse {
    private String imageCode;

    private String uuid;

}
