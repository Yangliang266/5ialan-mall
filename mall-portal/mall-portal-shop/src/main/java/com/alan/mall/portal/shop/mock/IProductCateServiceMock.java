package com.alan.mall.portal.shop.mock;

import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.AllProductCateRequest;
import com.alan.mall.service.shop.api.dto.AllProductCateResponse;
import com.alan.mall.service.shop.api.manager.IProductCateService;

public class IProductCateServiceMock implements IProductCateService {
    @Override
    public AllProductCateResponse getProductCate(AllProductCateRequest allProductCateRequest) {
        System.out.println("SYSTEM_TIMEOUT null");
        AllProductCateResponse response = new AllProductCateResponse();
        response.setCode(ShoppingRetCode.SYSTEM_TIMEOUT.getCode());
        response.setMsg(ShoppingRetCode.SYSTEM_TIMEOUT.getMessage());
        return response;
    }
}
