package com.alan.mall.service.user.api.dto;


import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import lombok.Data;

@Data
public class DeleteAddressRequest extends AbstractRequest {
    private Long addressId;
    private Long userId;

    @Override
    public void requestCheck() {
        if (null == addressId || null == userId) {
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
