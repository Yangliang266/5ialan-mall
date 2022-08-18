package com.alan.mall.service.shop.api.manager;


import com.alan.mall.service.shop.api.dto.AllProductCateRequest;
import com.alan.mall.service.shop.api.dto.AllProductCateResponse;

public interface IProductCateService {
    AllProductCateResponse getProductCate(AllProductCateRequest request);
}
