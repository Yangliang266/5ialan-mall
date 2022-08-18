package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.result.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AllProductResponse extends AbstractResponse {

    private List<ProductDto> productDtoList;

    private Long total;
}
