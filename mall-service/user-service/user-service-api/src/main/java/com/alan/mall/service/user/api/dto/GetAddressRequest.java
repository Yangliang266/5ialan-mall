package com.alan.mall.service.user.api.dto;


import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import lombok.Data;

@Data
public class GetAddressRequest extends AbstractRequest {
    private Long userId;

    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
