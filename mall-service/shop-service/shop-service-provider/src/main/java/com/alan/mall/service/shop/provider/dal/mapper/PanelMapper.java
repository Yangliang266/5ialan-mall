package com.alan.mall.service.shop.provider.dal.mapper;

import com.alan.mall.common.tool.tkmybatis.TKMapper;
import com.alan.mall.service.shop.provider.dal.entitys.Panel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PanelMapper extends TKMapper<Panel> {
    List<Panel> selectPanelContentById(@Param("panelId")Integer panelId);
}