package com.alan.mall.service.shop.api.dto;

import com.alan.mall.common.core.result.AbstractRequest;
import lombok.Data;

@Data
public class AllProductRequest extends AbstractRequest {

    private Integer page;
    private Integer size;
    private String sort;
    private Long cid;
    private Integer priceGt;
    private Integer priceLte;

    @Override
    public void requestCheck() {
        if(page<=0){
            setPage(1);
        }
    }
}
