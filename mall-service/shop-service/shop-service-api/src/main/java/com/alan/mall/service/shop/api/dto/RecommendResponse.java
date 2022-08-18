package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.result.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendResponse extends AbstractResponse {
    private Set<PanelDto> panelDtos;
}
