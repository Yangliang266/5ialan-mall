package com.alan.mall.portal.shop.controller;

import com.alan.mall.common.core.result.RespResult;
import com.alan.mall.portal.shop.forms.AddressForm;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.user.api.dto.*;
import com.alan.mall.service.user.api.manager.IAddressService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import com.alan.mall.service.user.sdk.intercepter.TokenInterceptor;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/shopping")
@Slf4j
public class AddressController {
    @DubboReference(check = false)
    IAddressService iAddressService;

    @GetMapping("/addresses")
    public RespResult<List<AddressDto>> addressList(HttpServletRequest request) {
        GetAddressRequest getAddressRequest = new GetAddressRequest();
        // 获取uid
        String info = (String) request.getAttribute(TokenInterceptor.USER_INFO_KEY);
        JSONObject object = JSON.parseObject(info);
        Long uid = Long.parseLong(object.get("uid").toString());
        getAddressRequest.setUserId(uid);

        GetAddressResponse addressResponse = iAddressService.getAddressDetails(getAddressRequest);
        if (addressResponse.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(addressResponse.getAddressDtos(),addressResponse.getMsg());
        }
        return RespResult.error(addressResponse.getMsg());
    }

    // 添加地址信息
    @PostMapping("/addresses")
    public RespResult<String> addAddress(@RequestBody AddressForm addressForm, HttpServletRequest servletRequest) {
        String userInfo = (String) servletRequest.getAttribute(TokenInterceptor.USER_INFO_KEY);
        JSONObject object = JSON.parseObject(userInfo);
        Long uid = Long.parseLong(object.get("uid").toString());

        AddressAddRequest request = new AddressAddRequest();
        request.setUserId(addressForm.getAddressId());
        request.setStreetName(addressForm.getStreetName());
        request.setTel(addressForm.getTel());
        request.setUserName(addressForm.getUserName());
        request.setUserId(uid);
        request.setIsDefault(addressForm.is_Default() ? 1 : null);

        AddressAddResponse response = iAddressService.addAddress(request);

        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getMsg());
        }

        return RespResult.error(response.getMsg());
    }

    // 更新地址信息
    @PutMapping("/addresses")
    public RespResult<String> updateAddress(@RequestBody AddressForm addressForm, HttpServletRequest servletRequest) {
        String userInfo = (String) servletRequest.getAttribute(TokenInterceptor.USER_INFO_KEY);
        JSONObject object = JSON.parseObject(userInfo);
        Long uid = Long.parseLong(object.get("uid").toString());

        AddressUpdateRequest request = new AddressUpdateRequest();
        request.setStreetName(addressForm.getStreetName());
        request.setTel(addressForm.getTel());
        request.setUserName(addressForm.getUserName());
        request.setUserId(uid);
        request.setAddressId(addressForm.getAddressId());
        request.setIsDefault(addressForm.is_Default() ? 1 : null);

        AddressUpdateResponse response = iAddressService.updateAddress(request);

        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getMsg());
        }
        return RespResult.error(response.getMsg());
    }

    @DeleteMapping("/addresses/{userId}/{addressId}")
    public RespResult<String> deleteAddress(@PathVariable long userId,@PathVariable long addressId) {
        DeleteAddressRequest request = new DeleteAddressRequest();
        request.setUserId(userId);
        request.setAddressId(addressId);
        DeleteAddressResponse response = iAddressService.deleteAddress(request);

        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return RespResult.ok(response.getMsg());
        }
        return RespResult.error(response.getMsg());
    }

}
