package com.alan.mall.service.shop.provider.service;

import com.alan.mall.common.tool.redis.RedissonWrapperClient;
import com.alan.mall.service.shop.api.constants.GlobalShopConstants;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.HomePageResponse;
import com.alan.mall.service.shop.api.dto.PanelDto;
import com.alan.mall.service.shop.api.manager.IHomeService;
import com.alan.mall.service.shop.provider.converter.ContentConverter;
import com.alan.mall.service.shop.provider.dal.entitys.Panel;
import com.alan.mall.service.shop.provider.dal.entitys.PanelContentItem;
import com.alan.mall.service.shop.provider.dal.mapper.PanelContentMapper;
import com.alan.mall.service.shop.provider.dal.mapper.PanelMapper;
import com.alan.mall.service.shop.provider.utils.ExceptionProcessorUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@DubboService
public class HomeServiceImpl implements IHomeService {
    @Autowired
    PanelContentMapper panelContentMapper;

    @Autowired
    ContentConverter contentConverter;

    @Autowired
    RedissonWrapperClient cacheManager;
//    CacheManager cacheManager;

    @Autowired
    PanelMapper panelMapper;

    @Override
//    @Cacheable(cacheNames = GlobalShopConstants.HOMEPAGE_CACHE_KEY)
    public HomePageResponse home() {
        log.info("Begin: HomeServiceImpl.homepage");
        HomePageResponse response=new HomePageResponse();
        response.setCode(ShoppingRetCode.SUCCESS.getCode());
        response.setMsg(ShoppingRetCode.SUCCESS.getMessage());

        try {
            String json= cacheManager.checkCache(GlobalShopConstants.HOMEPAGE_CACHE_KEY);
            if(StringUtils.isNoneEmpty(json)){
                List<PanelDto> panelDtoList=JSON.parseArray(json,PanelDto.class);
                Set set=new HashSet(panelDtoList);
                response.setPanelContentItemDtos(set);
                log.info("End: HomeServiceImpl.homepage.response: " + response);
                return response;
            }
            Example panelExample = new Example(Panel.class);
            panelExample.createCriteria().
                andEqualTo("position", 0).
                andEqualTo("status", 1);
            panelExample.setOrderByClause("sort_order");

            List<Panel> panels = panelMapper.selectByExample(panelExample);
            Set<PanelDto> panelContentItemDtos = new HashSet<PanelDto>();
            panels.parallelStream().forEach(panel -> {
                List<PanelContentItem> panelContentItems = panelContentMapper.selectPanelContentAndProductWithPanelId(panel.getId());
                PanelDto panelDto = contentConverter.panen2Dto(panel);
                panelDto.setPanelContentItems(contentConverter.panelContentItem2Dto(panelContentItems));
                panelContentItemDtos.add(panelDto);
            });

            //-----------------------  统一使用 spring cache 注解 --------------------
//            cacheManager.setCache(GlobalShopConstants.HOMEPAGE_CACHE_KEY,JSON.toJSONString(panelContentItemDtos),GlobalShopConstants.HOMEPAGE_EXPIRE_TIME);

            cacheManager.setCache(GlobalShopConstants.HOMEPAGE_CACHE_KEY,JSON.toJSONString(panelContentItemDtos));
            //-----------------------  统一使用 spring cache 注解 --------------------

            response.setPanelContentItemDtos(panelContentItemDtos);
        }catch (Exception e){
            log.error("Error: HomeServiceImpl.homepage.Exception :" + e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }

        log.info("End: HomeServiceImpl.homepage.response: " + response);
        return response;
    }
}
