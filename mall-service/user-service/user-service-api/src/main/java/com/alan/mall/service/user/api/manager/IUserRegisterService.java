package com.alan.mall.service.user.api.manager;

import com.alan.mall.service.user.api.dto.UserRegisterRequest;
import com.alan.mall.service.user.api.dto.UserRegisterResponse;

public interface IUserRegisterService {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
}
