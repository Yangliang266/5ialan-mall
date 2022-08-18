package com.alan.mall.service.user.api.dto;

import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class UserRegisterRequest extends AbstractRequest {

    private String userName;
    private String userPwd;
    private String email;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(userPwd)){
            throw new ValidateException(SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
