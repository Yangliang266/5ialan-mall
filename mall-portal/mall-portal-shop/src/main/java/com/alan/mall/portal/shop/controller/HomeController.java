package com.alan.mall.portal.shop.controller;

import com.alan.mall.common.core.result.RespResult;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.*;
import com.alan.mall.service.shop.api.manager.IContentService;
import com.alan.mall.service.shop.api.manager.IHomeService;
import com.alan.mall.service.shop.api.manager.IProductCateService;
import com.alan.mall.service.user.sdk.annotation.Anoymous;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequestMapping("/shopping")
@RestController
public class HomeController {
    @DubboReference(check = false, mock = "com.alan.mall.portal.shop.mock.IHomeServiceMock")
    IHomeService iHomeService;

    @DubboReference(check = false, mock = "com.alan.mall.portal.shop.mock.IContentServiceMock")
    IContentService iContentService;

    @DubboReference(check = false, mock = "com.alan.mall.portal.shop.mock.IProductCateServiceMock")
    IProductCateService iProductCateService;

    @Anoymous
    @GetMapping("/homepage")
    public RespResult<Set<PanelDto>> homepage() {
        HomePageResponse response=iHomeService.home();
        if(ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            return RespResult.ok(response.getPanelContentItemDtos());
        }
        return RespResult.error(response.getMsg());
    }

    @Anoymous
    @GetMapping("/navigation")
    public RespResult<List<PanelContentDto>> navigate() {
        NavListResponse response = iContentService.queryNavList();

        if(ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            return RespResult.ok(response.getPannelContentDtos());
        }
        return RespResult.error(response.getMsg());
    }

    @Anoymous
    @GetMapping("/categories")
    public  RespResult<List<ProductCateDto>> productCates(@RequestParam(value = "sort", required = false, defaultValue = "1") String sort) {
        AllProductCateRequest request = new AllProductCateRequest();
        request.setSort(sort);

        AllProductCateResponse response = iProductCateService.getProductCate(request);

        if(ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            return RespResult.ok(response.getProductCateDtoList());
        }
        return RespResult.error(response.getMsg());

    }
}
