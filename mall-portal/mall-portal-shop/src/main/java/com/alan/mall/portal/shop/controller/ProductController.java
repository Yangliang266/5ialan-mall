package com.alan.mall.portal.shop.controller;

import com.alan.mall.common.core.result.RespResult;
import com.alan.mall.portal.shop.forms.PageInfo;
import com.alan.mall.portal.shop.forms.PageResponse;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.*;
import com.alan.mall.service.shop.api.manager.IProductService;
import com.alan.mall.service.user.sdk.annotation.Anoymous;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/shopping")
@RestController
public class ProductController {

    @DubboReference(check = false)
    IProductService iProductService;

    @Anoymous
    @GetMapping("/product/{id}")
    public RespResult<ProductDetailDto> product(@PathVariable long id) {
        ProductDetailRequest request = new ProductDetailRequest();
        request.setId(id);
        request.requestCheck();
        ProductDetailResponse response = iProductService.getProductDetail(request);
        if(response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getProductDetailDto());
        }
        return RespResult.error(response.getMsg());
    }

    @Anoymous
    @GetMapping("/goods")
    public RespResult<PageResponse> goods(PageInfo pageInfo) {
        AllProductRequest request = new AllProductRequest();
        request.setCid(pageInfo.getCid());
        request.setPage(pageInfo.getPage());
        request.setPriceGt(pageInfo.getPriceGt());
        request.setPriceLte(pageInfo.getPriceLte());
        request.setSize(pageInfo.getSize());
        request.setSort(pageInfo.getSort());

        AllProductResponse response=iProductService.getAllProduct(request);
        if(response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            PageResponse pageResponse=new PageResponse();
            pageResponse.setData(response.getProductDtoList());
            pageResponse.setTotal(response.getTotal());
            return RespResult.ok(pageResponse);
        }
        return RespResult.error(response.getMsg());
    }

    @Anoymous
    @GetMapping("/recommend")
    public RespResult<Set<PanelDto>> recommend() {
        RecommendResponse response = iProductService.recommend();

        if(response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getPanelDtos());
        }
        return RespResult.error(response.getMsg());
    }

}
