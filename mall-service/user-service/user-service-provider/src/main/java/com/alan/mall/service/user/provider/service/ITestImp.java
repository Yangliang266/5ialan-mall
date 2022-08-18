package com.alan.mall.service.user.provider.service;

import com.alan.mall.service.user.api.manager.ITest;
import com.alan.mall.service.user.provider.dal.entitys.Address;
import com.alan.mall.service.user.provider.dal.mapper.AddressMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: mathyoung
 * @description:
 */
@DubboService
public class ITestImp implements ITest {
    private final static String TEST = "':address_info'";

    @Autowired
    AddressMapper addressMapper;

    @Override
    @Cacheable(value = "test_info=3000",key = "#str")
    public String test(String str) {
        System.out.println("未使用redis");
        Example example = new Example(Address.class);
        example.createCriteria().andEqualTo("userName",str);

        List<Address> addresses = addressMapper.selectByExample(example);

        return addresses.toString();
    }
}
