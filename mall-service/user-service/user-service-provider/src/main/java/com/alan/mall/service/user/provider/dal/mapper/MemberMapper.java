package com.alan.mall.service.user.provider.dal.mapper;

import com.alan.mall.common.tool.tkmybatis.TKMapper;
import com.alan.mall.service.user.provider.dal.entitys.Member;
import org.springframework.stereotype.Component;

@Component
public interface MemberMapper extends TKMapper<Member> {
    Member selectById(Long id);
}