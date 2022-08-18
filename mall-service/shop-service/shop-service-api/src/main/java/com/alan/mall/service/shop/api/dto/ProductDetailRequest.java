package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDetailRequest extends AbstractRequest {

    private Long id;

    @Override
    public void requestCheck() {
        if(id==null){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(), ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
