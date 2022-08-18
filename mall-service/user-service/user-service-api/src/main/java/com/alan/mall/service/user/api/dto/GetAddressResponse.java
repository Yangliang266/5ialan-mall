package com.alan.mall.service.user.api.dto;

import com.alan.mall.common.core.result.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class GetAddressResponse extends AbstractResponse {
    private List<AddressDto> addressDtos;
}
