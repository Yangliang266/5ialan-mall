package com.alan.mall.portal.shop.mock;


import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.NavListResponse;
import com.alan.mall.service.shop.api.manager.IContentService;

public class IContentServiceMock implements IContentService {
    @Override
    public NavListResponse queryNavList() {
        System.out.println("SYSTEM_TIMEOUT null");
        NavListResponse response = new NavListResponse();
        response.setCode(ShoppingRetCode.SYSTEM_TIMEOUT.getCode());
        response.setMsg(ShoppingRetCode.SYSTEM_TIMEOUT.getMessage());
        return response;
    }
}
