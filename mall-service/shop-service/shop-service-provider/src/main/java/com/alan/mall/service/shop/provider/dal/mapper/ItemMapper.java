package com.alan.mall.service.shop.provider.dal.mapper;

import com.alan.mall.common.tool.tkmybatis.TKMapper;
import com.alan.mall.service.shop.api.dto.sqldto.PageInfoDto;
import com.alan.mall.service.shop.provider.dal.entitys.Item;

import java.util.List;

public interface ItemMapper extends TKMapper<Item> {
    List<Item> selectItemFront(PageInfoDto pageInfoDto);
}