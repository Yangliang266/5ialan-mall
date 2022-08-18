package com.alan.mall.service.user.api.dto;

import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryMemberRequest extends AbstractRequest {
    private static final long serialVersionUID = 3722427112800721315L;

    private Long userId;

    @Override
    public void requestCheck() {
        if (null == userId) {
            throw new ValidateException(SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(), SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
