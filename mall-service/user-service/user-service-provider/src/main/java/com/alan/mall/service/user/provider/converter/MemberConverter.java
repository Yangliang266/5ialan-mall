package com.alan.mall.service.user.provider.converter;


import com.alan.mall.service.user.api.dto.QueryMemberResponse;
import com.alan.mall.service.user.provider.dal.entitys.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MemberConverter {
    @Mappings({})
    QueryMemberResponse member2Res(Member request);
}
