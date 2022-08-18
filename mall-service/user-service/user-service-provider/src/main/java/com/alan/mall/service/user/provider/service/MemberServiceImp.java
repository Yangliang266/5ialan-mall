package com.alan.mall.service.user.provider.service;

import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import com.alan.mall.service.user.api.dto.QueryMemberRequest;
import com.alan.mall.service.user.api.dto.QueryMemberResponse;
import com.alan.mall.service.user.api.manager.IMemberService;
import com.alan.mall.service.user.provider.converter.MemberConverter;
import com.alan.mall.service.user.provider.dal.entitys.Member;
import com.alan.mall.service.user.provider.dal.mapper.MemberMapper;
import com.alan.mall.service.user.provider.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@DubboService
@Component
public class MemberServiceImp implements IMemberService {
    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberConverter memberConverter;

    @Override
    public QueryMemberResponse queryMemberById(QueryMemberRequest request) {
        log.info("Begin: MemberServiceImp.queryMemberById.request: " + request.getUserId());
        QueryMemberResponse response = new QueryMemberResponse();
        try {
            Member member = memberMapper.selectById(request.getUserId());
            if (null != member) {
                response = memberConverter.member2Res(member);
                response.setCode(SysRetCodeConstants.SUCCESS.getCode());
                response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            }
        } catch (Exception e) {
            log.error("Exception: MemberServiceImp.queryMemberById.response: " + response);
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        return response;
    }
}
