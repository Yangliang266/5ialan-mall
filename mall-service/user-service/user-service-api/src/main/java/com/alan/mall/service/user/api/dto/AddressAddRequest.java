package com.alan.mall.service.user.api.dto;


import com.alan.mall.common.core.exception.ValidateException;
import com.alan.mall.common.core.result.AbstractRequest;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class AddressAddRequest extends AbstractRequest {
    private Long userId;

    private String userName;

    private String tel;

    private String streetName;

    private Integer isDefault;

    @Override
    public void requestCheck() {
        if(userId==null|| StringUtils.isBlank(streetName)||StringUtils.isBlank(tel)||StringUtils.isBlank(userName)){
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
