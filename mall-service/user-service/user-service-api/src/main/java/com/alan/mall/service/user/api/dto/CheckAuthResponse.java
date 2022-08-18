package com.alan.mall.service.user.api.dto;

import com.alan.mall.common.core.result.AbstractResponse;
import lombok.Data;

/**
 * @Author yangl
 * @Description //TODO
 * @Date 12:12 2020/9/24
 * @Param
 * @return
 **/
@Data
public class CheckAuthResponse extends AbstractResponse {

    private String userinfo;
}
