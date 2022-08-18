package com.alan.mall.service.shop.provider.utils;


import com.alan.mall.common.core.exception.AbstractExceptionTemplate;
import com.alan.mall.common.core.exception.ExceptionUtil;
import com.alan.mall.common.core.result.AbstractResponse;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;

public class ExceptionProcessorUtils extends AbstractExceptionTemplate {
    public static void wrapperHandlerException(AbstractResponse response, Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(ShoppingRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(ShoppingRetCode.SYSTEM_ERROR.getMessage());
        }
    }
}
