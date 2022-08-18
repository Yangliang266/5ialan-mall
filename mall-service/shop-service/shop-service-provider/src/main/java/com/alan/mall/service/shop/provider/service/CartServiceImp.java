package com.alan.mall.service.shop.provider.service;

import com.alan.mall.common.core.exception.AbstractExceptionTemplate;
import com.alan.mall.common.tool.redis.RedissonWrapperClient;
import com.alan.mall.service.shop.api.constants.GlobalShopConstants;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.*;
import com.alan.mall.service.shop.api.manager.ICartService;
import com.alan.mall.service.shop.provider.converter.CartItemConverter;
import com.alan.mall.service.shop.provider.dal.entitys.Item;
import com.alan.mall.service.shop.provider.dal.mapper.ItemMapper;
import com.alan.mall.service.shop.provider.utils.ExceptionProcessorUtils;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@DubboService
@Slf4j
public class CartServiceImp implements ICartService {
    @Autowired
    ItemMapper itemMapper;

    @Autowired
    CartItemConverter cartItemConverter;

    @Autowired
    RedissonWrapperClient redissonConfig;

    @Override
    public CartListByIdResponse getCartListById(CartListByIdRequest cartListByIdRequest) {
        log.info("Begin: CartServiceImp.getCartListById.request: " + cartListByIdRequest);
        cartListByIdRequest.requestCheck();
        String cartCacheKey = GlobalShopConstants.generatorKey(cartListByIdRequest.getUserId(), GlobalShopConstants.CART_ITEM_CACHE_PREFIX);
        CartListByIdResponse cartListByIdResponse = new CartListByIdResponse();
        cartListByIdResponse.setCode(ShoppingRetCode.SUCCESS.getCode());
        cartListByIdResponse.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        List<CartProductDto> list = new ArrayList<>();
        try {
            // 查询redis
            RMap<String, String> items = redissonConfig.getMap(cartCacheKey);
            // 可能会有多个产品被添加到购物车

            items.values().forEach(obj -> {
                // obj转换
                CartProductDto cartProductDto = JSON.parseObject(obj.toString(), CartProductDto.class);
                list.add(cartProductDto);
            });

            cartListByIdResponse.setCartProductDto(list);
            log.info("End: CartServiceImp.getCartListById.response: " + cartListByIdResponse);
            return cartListByIdResponse;
        } catch (Exception e) {
            log.error("Error: CartServiceImpl.getCartListById.Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(cartListByIdResponse, e);
        }
        log.info("End: CartServiceImp.getCartListById.response: " + cartListByIdResponse);
        return cartListByIdResponse;
    }

    @Override
    @CacheEvict(value = GlobalShopConstants.CART_ITEM_CACHE_PREFIX, key = "#request.userId")
    public DeleteCartItemResponse deleteCartItem(DeleteCartItemRequest request) {
        log.info("Begin: CartServiceImp.deleteCartItem.request: " + request);
        request.requestCheck();
        DeleteCartItemResponse response = new DeleteCartItemResponse();
        try {
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("Error: CartServiceImp.deleteCartItem.Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        log.info("End: CartServiceImp.deleteCartItem.response: " + response);
        return response;
    }

    @Override
    public DeleteCheckedItemResposne deleteCheckedItems(DeleteCheckedItemRequest request) {
        log.info("Begin: CartServiceImp.deleteCheckedItems.request: " + request);
        request.requestCheck();
        String cartCacheKey = GlobalShopConstants.generatorKey(request.getUserId(), GlobalShopConstants.CART_ITEM_CACHE_PREFIX);
        DeleteCheckedItemResposne resposne = new DeleteCheckedItemResposne();
        try {
            RMap<String, String> items = redissonConfig.getMap(cartCacheKey);
            items.values().forEach(obj  -> {
                CartProductDto cartProductDto = JSON.parseObject(obj, CartProductDto.class);
                if ("true".equals(cartProductDto.getChecked())) {
                    items.remove(cartProductDto.getProductId().toString());
                }
            });
            resposne.setCode(ShoppingRetCode.SUCCESS.getCode());
            resposne.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("Error: CartServiceImp.deleteCheckedItems.Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(resposne, e);
        }

        log.info("End: CartServiceImp.deleteCheckedItems.response: " + resposne);
        return resposne;
    }

    @Override
    public UpdateCartNumResponse updateCartNum(UpdateCartNumRequest request) {
        log.info("Begin: CartServiceImp.updateCartNum.request: " + request);
        request.requestCheck();
        String cartCacheKey = GlobalShopConstants.generatorKey(request.getUserId(), GlobalShopConstants.CART_ITEM_CACHE_PREFIX);
        UpdateCartNumResponse response = new UpdateCartNumResponse();

        try {
            if (redissonConfig.checkMapCache(
                    cartCacheKey,
                    request.getItemId().toString())) {
                // 解析json
                String json = redissonConfig.getMapField(
                        cartCacheKey,
                        request.getItemId().toString());

                if(StringUtils.isNotBlank(json)) {
                    CartProductDto cartProductDto = JSON.parseObject(json, CartProductDto.class);
                    // 更新redis 产品数量
                    cartProductDto.setProductNum(request.getNum().longValue());
                    // 设置可选
                    cartProductDto.setChecked(request.getChecked());
                    // 设置新的数据到redis
                    redissonConfig.setMapCache(
                            cartCacheKey,
                            request.getItemId().toString(),
                            JSON.toJSON(cartProductDto).toString());
                }
                response.setCode(ShoppingRetCode.SUCCESS.getCode());
                response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
            }
        } catch (Exception e) {
            log.error("Error: CartServiceImp.updateCartNum.Exception :" + e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }

        log.info("End: CartServiceImp.updateCartNum.response " + response);
        return response;
    }

    @Override
    public SelectAllItemResponse selectAllItem(SelectAllItemRequest request) {
        log.info("Begin: CartServiceImp.selectAllItem.request: " + request);
        request.requestCheck();
        String cartCacheKey = GlobalShopConstants.generatorKey(request.getUserId(), GlobalShopConstants.CART_ITEM_CACHE_PREFIX);
        SelectAllItemResponse response = new SelectAllItemResponse();
        try {
            RMap<String, String> items = redissonConfig.getMap(cartCacheKey);
            items.values().forEach(obj ->{
                // 解析成对象
                CartProductDto cartProductDto= JSON.parseObject(obj, CartProductDto.class);
                // redis设置成全选
                cartProductDto.setChecked(request.getCheck());//true / false
                // 转换成string
                String json = JSON.toJSON(cartProductDto).toString();
                items.put(cartProductDto.getProductId().toString(),json);
            });
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("Error: CartServiceImp.selectAllItem.Exception :" + e);
            AbstractExceptionTemplate.wrapperHandlerException(response, e);
        }

        log.info("End: CartServiceImp.selectAllItem.response: " + response);
        return response;
    }

    @Override
    public ClearCartItemResponse clearCartItemByUserID(ClearCartItemRequest request) {
        ClearCartItemResponse response=new ClearCartItemResponse();
        try{
            RMap<String, String> itemMap = redissonConfig.getMap(GlobalShopConstants.generatorKey(request.getUserId(), GlobalShopConstants.CART_ITEM_CACHE_PREFIX));
            itemMap.values().forEach(obj -> {
                CartProductDto cartProductDto = JSON.parseObject(obj, CartProductDto.class);
                if(request.getProductIds().contains(cartProductDto.getProductId())){
                    itemMap.remove(cartProductDto.getProductId().toString());
                }
            });
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        }catch (Exception e){
            log.error("CartServiceImpl.clearCartItemByUserID Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public AddCartResponse addToCart(AddCartRequest addCartRequest) {
        log.info("Begin: CartServiceImp.addToCart.request: " + addCartRequest);
        addCartRequest.requestCheck();
        String cartCacheKey =  GlobalShopConstants.generatorKey(addCartRequest.getUserId(), GlobalShopConstants.CART_ITEM_CACHE_PREFIX);
        AddCartResponse addCartResponse = new AddCartResponse();
        addCartResponse.setCode(ShoppingRetCode.SUCCESS.getCode());
        addCartResponse.setMsg(ShoppingRetCode.SUCCESS.getMessage());

        try {
            // check redis 是否存在此类商品信息
            if (redissonConfig.checkMapCache(
                    cartCacheKey,
                    addCartRequest.getItemId().toString())) {
                // 解析json
                String json = redissonConfig.getMapField(
                        cartCacheKey,
                        addCartRequest.getItemId().toString());

                CartProductDto cartProductDto = JSON.parseObject(json, CartProductDto.class);
                // 更新redis 产品数量
                cartProductDto.setProductNum(cartProductDto.getProductNum() + addCartRequest.getNum());
                // 设置新的数据到redis
                redissonConfig.setMapCache(
                        cartCacheKey,
                        addCartRequest.getItemId().toString(),
                        JSON.toJSON(cartProductDto).toString());

                log.info("End: CartServiceImp.addToCart.response: " + addCartResponse);
                return addCartResponse;
            }
            // sql 查询商品的具体信息
            Item item = itemMapper.selectByPrimaryKey(addCartRequest.getItemId());

            // 转换为dto 放入redis
            CartProductDto cartProductDto = CartItemConverter.item2Dto(item);
            cartProductDto.setChecked("true");
            cartProductDto.setProductNum(addCartRequest.getNum().longValue());

            if (null == item) {
                addCartResponse.setCode(ShoppingRetCode.SYSTEM_ERROR.getCode());
                addCartResponse.setMsg(ShoppingRetCode.SYSTEM_ERROR.getMessage());
                log.info("Error: CartServiceImpl.addToCart.itemisNull :" + addCartResponse);
                return addCartResponse;
            }
            // 存入缓存redis
            redissonConfig.setMapCache(
                    cartCacheKey,
                    addCartRequest.getItemId().toString(),
                    JSON.toJSON(cartProductDto).toString()
            );

        } catch (Exception e) {
            log.error("CartServiceImpl.addToCart Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(addCartResponse, e);
        }

        log.info("End: CartServiceImp.addToCart.response: " + addCartResponse);
        return addCartResponse;
    }

}
