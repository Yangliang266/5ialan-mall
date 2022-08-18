package com.alan.mall.service.shop.api.manager;

import com.alan.mall.service.shop.api.dto.*;

public interface IProductService {
    ProductDetailResponse getProductDetail(ProductDetailRequest request);

    AllProductResponse getAllProduct(AllProductRequest request);

    RecommendResponse recommend();
}
