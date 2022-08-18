package com.alan.mall.service.user.api.manager;


import com.alan.mall.service.user.api.dto.CheckAuthRequest;
import com.alan.mall.service.user.api.dto.CheckAuthResponse;
import com.alan.mall.service.user.api.dto.UserLoginRequest;
import com.alan.mall.service.user.api.dto.UserLoginResponse;

public interface IUserLoginService {

    /**
     * 用户登录
     * @param userLoginRequest
     * @return
     */
    UserLoginResponse login(UserLoginRequest userLoginRequest);

    /**
     * token 验证
     * @param checkAuthRequest
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest checkAuthRequest);


}
