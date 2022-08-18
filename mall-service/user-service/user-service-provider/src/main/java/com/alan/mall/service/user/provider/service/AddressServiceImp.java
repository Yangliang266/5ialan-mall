package com.alan.mall.service.user.provider.service;

import com.alan.mall.common.tool.redis.RedissonWrapperClient;
import com.alan.mall.service.user.api.constants.GlobalUserConstants;
import com.alan.mall.service.user.api.constants.SysRetCodeConstants;
import com.alan.mall.service.user.api.dto.*;
import com.alan.mall.service.user.api.manager.IAddressService;
import com.alan.mall.service.user.provider.dal.entitys.Address;
import com.alan.mall.service.user.provider.dal.mapper.AddressMapper;
import com.alan.mall.service.user.provider.utils.ExceptionProcessorUtils;
import com.alan.mall.service.user.provider.converter.AddressConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@DubboService
@Slf4j
public class AddressServiceImp implements IAddressService {
    @Autowired
    RedissonWrapperClient redissonClient;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressConverter addressConverter;

    @Override
    @Cacheable(value = GlobalUserConstants.USER_INFO , key = GlobalUserConstants.ADDRESS_CACHE_KEY + "+#request.userId")
    public GetAddressResponse getAddressDetails(GetAddressRequest request) {
        log.info("Begin: IaddressService.getAddressDetails.request: " + request);
        request.requestCheck();
        GetAddressResponse response = new GetAddressResponse();
        response.setCode(SysRetCodeConstants.SUCCESS.getCode());
        response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        try {
            // sql 查询set
            Example example = new Example(Address.class);
            example.createCriteria().andEqualTo("userId",request.getUserId());
            List<Address> addressDtos = addressMapper.selectByExample(example);
            List<AddressDto> addressDtoList = addressConverter.address2List(addressDtos);
            response.setAddressDtos(addressDtoList);
        } catch (Exception e) {
            log.error("Error: IaddressService.getAddressDetails.Exception: " + e );
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        log.info("End: IaddressService.getAddressDetails.response: " + response);
        return response;
    }

    @Override
    @CacheEvict(value = GlobalUserConstants.USER_INFO, key = GlobalUserConstants.ADDRESS_CACHE_KEY + "+#request.userId")
    public DeleteAddressResponse deleteAddress(DeleteAddressRequest request) {
        log.info("Begin: IaddressService.deleteAddress.request: " + request);
        request.requestCheck();
        DeleteAddressResponse response = new DeleteAddressResponse();
        try {
            Example example = new Example(Address.class);
            example.createCriteria().andEqualTo("addressId",request.getAddressId());
            addressMapper.deleteByExample(example);
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("Error: IaddressService.deleteAddress.Exception: " + e );
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        log.info("End: IaddressService.deleteAddress.response: " + response);
        return response;
    }

    @Override
    @CacheEvict(value = GlobalUserConstants.USER_INFO, key = GlobalUserConstants.ADDRESS_CACHE_KEY + "+#request.userId")
    public AddressAddResponse addAddress(AddressAddRequest request) {
        log.info("Begin: IaddressService.addAddress.request: " + request);
        request.requestCheck();
        AddressAddResponse response = new AddressAddResponse();
        try {
            Address address = addressConverter.addReq2Address(request);
            int row = addressMapper.insert(address);
            if (row > 0) {
                response.setCode(SysRetCodeConstants.SUCCESS.getCode());
                response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            }
        } catch (Exception e) {
            log.error("Error: IaddressService.addAddress.Exception: " + e );
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        log.info("End: IaddressService.addAddress.response: " + response);
        return response;
    }

    @Override
    @CacheEvict(value = GlobalUserConstants.USER_INFO, key = GlobalUserConstants.ADDRESS_CACHE_KEY + "+#request.userId")
    public AddressUpdateResponse updateAddress(AddressUpdateRequest request) {
        log.info("Begin :IaddressService.updateAddress.request: " + request);
        request.requestCheck();
        AddressUpdateResponse response = new AddressUpdateResponse();
        try {
            Address address = addressConverter.upRes2Address(request);
            int row = addressMapper.updateByPrimaryKey(address);
            if (row > 0) {
                response.setCode(SysRetCodeConstants.SUCCESS.getCode());
                response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            }
        } catch (Exception e) {
            log.error("Error: IaddressService.updateAddress.Exception: " + e );
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        log.info("End: IaddressService.updateAddress.response: " + response);
        return response;
    }
}
