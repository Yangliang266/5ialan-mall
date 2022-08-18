package com.alan.mall.portal.shop.controller;

import com.alan.mall.common.core.globalvar.TokenGlobal;
import com.alan.mall.common.core.result.RespResult;
import com.alan.mall.portal.shop.forms.CartForm;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.*;
import com.alan.mall.service.shop.api.manager.ICartService;
import com.alan.mall.service.user.sdk.intercepter.TokenInterceptor;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class CartController {
    @DubboReference(check = false)
    ICartService iCartService;

    // 添加到购物车
    @PostMapping("/carts")
    public RespResult<String> addToCart(@RequestBody CartForm cartForm) {
        // 传参
        AddCartRequest addCartRequest = new AddCartRequest();
        addCartRequest.setItemId(cartForm.getProductId());
        addCartRequest.setUserId(cartForm.getUserId());
        addCartRequest.setNum(cartForm.getProductNum());
        // 验证
        addCartRequest.requestCheck();
        // 加入redis缓存
        AddCartResponse addCartResponse = iCartService.addToCart(addCartRequest);

        return RespResult.ok(addCartResponse.getMsg());
    }

    // 获取购物车列表
    @GetMapping("/carts")
    public RespResult<List<CartProductDto>> carts(HttpServletRequest request) {
        // 获取之前存储的token信息 包含uid
        String userInfo = (String) request.getAttribute(TokenInterceptor.USER_INFO_KEY);
        // 获取userId 解析
        long uid = Long.parseLong(JSON.parseObject(userInfo).getString("uid"));

        CartListByIdRequest cartListByIdRequest = new CartListByIdRequest();
        cartListByIdRequest.setUserId(uid);
        CartListByIdResponse response = iCartService.getCartListById(cartListByIdRequest);
        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getCartProductDto());
        }
        return RespResult.error(response.getMsg());
    }

    // 删除购物车
    @DeleteMapping("/carts/{uid}/{pid}")
    public RespResult<String> deleteCarts(@PathVariable long uid, @PathVariable long pid) {
        DeleteCartItemRequest request = new DeleteCartItemRequest();
        request.setUserId(uid);
        request.setItemId(pid);
        DeleteCartItemResponse response = iCartService.deleteCartItem(request);
        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getMsg());
        }
        return RespResult.error(response.getMsg());
    }

    // 更新购物车数量
    @PutMapping("/carts")
    public RespResult<String> updateCarts(@RequestBody CartForm cartForm) {
        UpdateCartNumRequest request = new UpdateCartNumRequest();
        request.setUserId(cartForm.getUserId());
        request.setChecked(cartForm.getChecked());
        request.setItemId(cartForm.getProductId());
        request.setNum(cartForm.getProductNum());

        UpdateCartNumResponse response = iCartService.updateCartNum(request);

        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getMsg());
        }
        return RespResult.error(response.getMsg());
    }

    // 全选商品
    @PutMapping("/items")
    public RespResult<String> selectAllItem(@RequestBody CartForm cartForm) {
        SelectAllItemRequest request = new SelectAllItemRequest();
        request.setCheck(cartForm.getChecked());
        request.setUserId(cartForm.getUserId());
        SelectAllItemResponse response = iCartService.selectAllItem(request);
        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getMsg());
        }
        return RespResult.error(response.getMsg());
    }

    @DeleteMapping("/items/{id}")
    public RespResult<String> deleteCheckedItems(@PathVariable long id) {
        DeleteCheckedItemRequest request = new DeleteCheckedItemRequest();
        request.setUserId(id);
        DeleteCheckedItemResposne resposne = iCartService.deleteCheckedItems(request);
        if (resposne.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(resposne.getMsg());
        }
        return RespResult.error(resposne.getMsg());
    }

}
