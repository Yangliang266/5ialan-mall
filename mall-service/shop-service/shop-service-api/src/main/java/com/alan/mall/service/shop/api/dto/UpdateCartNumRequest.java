package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateCartNumRequest extends AbstractRequest {
    private Long userId;
    private Long itemId;
    private Integer num;
    private String checked;

    @Override
    public void requestCheck() {
        if(userId==null||itemId==null||num==null|| StringUtils.isBlank(checked)){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(), ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
