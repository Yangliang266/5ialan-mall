package com.alan.mall.service.user.provider.converter;


import com.alan.mall.service.user.api.dto.UserLoginResponse;
import com.alan.mall.service.user.provider.dal.entitys.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({})
    UserLoginResponse login(Member member);
}
