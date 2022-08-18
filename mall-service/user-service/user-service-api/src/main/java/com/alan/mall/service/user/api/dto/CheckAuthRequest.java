package com.alan.mall.service.user.api.dto;


import com.alan.mall.common.core.result.AbstractRequest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author yangl
 * @Description //TODO
 * @Date 12:06 2020/9/24
 * @Param
 * @return
 **/
@Data
public class CheckAuthRequest extends AbstractRequest {
    private String token;

    @Override
    public void requestCheck() {
//        if(StringUtils.isBlank(token)){
//            throw new ValidateException(
//                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
//                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
//        }
    }
}
