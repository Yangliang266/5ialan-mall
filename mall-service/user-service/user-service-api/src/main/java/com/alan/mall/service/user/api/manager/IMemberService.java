package com.alan.mall.service.user.api.manager;

import com.alan.mall.service.user.api.dto.QueryMemberRequest;
import com.alan.mall.service.user.api.dto.QueryMemberResponse;

public interface IMemberService {
    QueryMemberResponse queryMemberById(QueryMemberRequest request);
}
