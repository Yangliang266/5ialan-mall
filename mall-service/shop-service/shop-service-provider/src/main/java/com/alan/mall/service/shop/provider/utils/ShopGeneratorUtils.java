package com.alan.mall.service.shop.provider.utils;


import com.alan.mall.service.shop.api.constants.GlobalShopConstants;

public class ShopGeneratorUtils {
    public String generatorCartItemKey(Long userId) {
        StringBuilder stringBuilder = new StringBuilder(GlobalShopConstants.CART_ITEM_CACHE_PREFIX);
        stringBuilder.append(":").append(userId);
        return stringBuilder.toString();
    }

    private ShopGeneratorUtils() {
    }

    public static ShopGeneratorUtils getInstance() {
        return ShopGeneratorInstance.instance;
    }

    private static class ShopGeneratorInstance {
        private final static ShopGeneratorUtils instance = new ShopGeneratorUtils();
    }


}
