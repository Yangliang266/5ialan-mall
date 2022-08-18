package com.alan.mall.service.shop.api.dto;


import com.alan.mall.common.core.result.AbstractRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AllProductCateRequest extends AbstractRequest {
    private String sort;

    @Override
    public void requestCheck() {

    }
}
