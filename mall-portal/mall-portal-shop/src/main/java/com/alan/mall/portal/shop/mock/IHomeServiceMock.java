package com.alan.mall.portal.shop.mock;


import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.HomePageResponse;
import com.alan.mall.service.shop.api.manager.IHomeService;

public class IHomeServiceMock implements IHomeService {
    @Override
    public HomePageResponse home() {
        System.out.println("SYSTEM_TIMEOUT");
        HomePageResponse response=new HomePageResponse();
        response.setCode(ShoppingRetCode.SYSTEM_TIMEOUT.getCode());
        response.setMsg(ShoppingRetCode.SYSTEM_TIMEOUT.getMessage());
        return response;
    }
}
