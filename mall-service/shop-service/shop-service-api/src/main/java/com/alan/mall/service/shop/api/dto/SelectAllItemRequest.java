package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@EqualsAndHashCode(callSuper = true)
@Data
public class SelectAllItemRequest extends AbstractRequest {
    private Long userId;
    private String check;

    @Override
    public void requestCheck() {
        if (null == userId || StringUtils.isBlank(check)) {
            throw  new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
