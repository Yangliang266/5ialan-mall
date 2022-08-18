package com.alan.mall.service.user.provider.converter;

import com.alan.mall.service.user.api.dto.AddressAddRequest;
import com.alan.mall.service.user.api.dto.AddressDto;
import com.alan.mall.service.user.api.dto.AddressUpdateRequest;
import com.alan.mall.service.user.provider.dal.entitys.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressConverter {

    @Mappings({})
    List<AddressDto> address2List(List<Address> addressList);

    @Mappings({})
    Address addReq2Address(AddressAddRequest request);

    @Mappings({})
    AddressDto address2AddressDto(Address address);

    @Mappings({})
    Address upRes2Address(AddressUpdateRequest request);

}
