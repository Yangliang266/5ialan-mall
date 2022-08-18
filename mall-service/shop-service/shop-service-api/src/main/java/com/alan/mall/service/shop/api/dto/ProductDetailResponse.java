package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.result.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDetailResponse extends AbstractResponse {
    private ProductDetailDto productDetailDto;
}
