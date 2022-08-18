package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.result.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClearCartItemResponse extends AbstractResponse {
    private static final long serialVersionUID = 330382636520948173L;
}
