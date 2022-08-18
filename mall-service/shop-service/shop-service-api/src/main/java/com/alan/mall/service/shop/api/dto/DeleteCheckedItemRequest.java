package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeleteCheckedItemRequest extends AbstractRequest {

    private Long userId;

    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());

        }
    }
}
