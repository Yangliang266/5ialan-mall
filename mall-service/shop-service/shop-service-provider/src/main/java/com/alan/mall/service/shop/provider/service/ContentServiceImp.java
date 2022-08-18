package com.alan.mall.service.shop.provider.service;

import com.alan.mall.common.tool.redis.RedissonWrapperClient;
import com.alan.mall.service.shop.api.constants.GlobalShopConstants;
import com.alan.mall.service.shop.api.constants.ShoppingRetCode;
import com.alan.mall.service.shop.api.dto.NavListResponse;
import com.alan.mall.service.shop.api.dto.PanelContentDto;
import com.alan.mall.service.shop.api.manager.IContentService;
import com.alan.mall.service.shop.provider.converter.ContentConverter;
import com.alan.mall.service.shop.provider.dal.entitys.PanelContent;
import com.alan.mall.service.shop.provider.dal.mapper.PanelContentMapper;
import com.alan.mall.service.shop.provider.utils.ExceptionProcessorUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@DubboService
@Slf4j
public class ContentServiceImp implements IContentService {
    @Autowired
    RedissonWrapperClient redissonConfig;

    @Autowired
    PanelContentMapper panelContentMapper;

    @Autowired
    ContentConverter contentConverter;

    @Override
//    @Cacheable(cacheNames = GlobalShopConstants.HEADER_PANEL_CACHE_KEY)
    public NavListResponse queryNavList() {
        log.info("Begin: ContentServiceImp.queryNavList");
        NavListResponse response = new NavListResponse();
        response.setCode(ShoppingRetCode.SUCCESS.getCode());
        response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        try {
            // redis 查询
            String json = redissonConfig.checkCache(GlobalShopConstants.HEADER_PANEL_CACHE_KEY);
            if (StringUtils.isNotBlank(json)) {
                List<PanelContentDto> panelContentDtoList = JSON.parseArray(json, PanelContentDto.class);
                response.setPannelContentDtos(panelContentDtoList);
                log.info("End: ContentServiceImp.queryNavList.response " + response);
                return response;
            }
            // sql 查询
            Example example = new Example(PanelContent.class);
            example.setOrderByClause("sort_order");
            example.createCriteria().andEqualTo("panelId", GlobalShopConstants.HEADER_PANEL_ID);
            List<PanelContent> panelContentList = panelContentMapper.selectByExample(example);
            List<PanelContentDto> panelContentDtoList = contentConverter.panelContents2Dto(panelContentList);
            //-----------------------  统一使用 spring cache 注解 --------------------
            redissonConfig.setCache(GlobalShopConstants.HEADER_PANEL_CACHE_KEY, JSON.toJSON(panelContentDtoList).toString());
            //-----------------------  统一使用 spring cache 注解 --------------------
            response.setPannelContentDtos(panelContentDtoList);

        } catch (Exception e) {
            log.info("error: ContentServiceImp.queryNavList.Exception :" + e);
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        log.info("End: ContentServiceImp.queryNavList.response " + response);
        return response;
    }
}
