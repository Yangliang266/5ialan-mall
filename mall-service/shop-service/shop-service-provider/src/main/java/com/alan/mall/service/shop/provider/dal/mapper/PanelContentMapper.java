package com.alan.mall.service.shop.provider.dal.mapper;

import com.alan.mall.common.tool.tkmybatis.TKMapper;
import com.alan.mall.service.shop.provider.dal.entitys.PanelContent;
import com.alan.mall.service.shop.provider.dal.entitys.PanelContentItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PanelContentMapper extends TKMapper<PanelContent> {
    List<PanelContentItem> selectPanelContentAndProductWithPanelId(@Param("panelId")Integer panelId);
}