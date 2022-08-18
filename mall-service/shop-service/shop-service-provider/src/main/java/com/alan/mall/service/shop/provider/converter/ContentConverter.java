package com.alan.mall.service.shop.provider.converter;

import com.alan.mall.service.shop.api.dto.PanelContentDto;
import com.alan.mall.service.shop.api.dto.PanelContentItemDto;
import com.alan.mall.service.shop.api.dto.PanelDto;
import com.alan.mall.service.shop.provider.dal.entitys.Panel;
import com.alan.mall.service.shop.provider.dal.entitys.PanelContent;
import com.alan.mall.service.shop.provider.dal.entitys.PanelContentItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentConverter {

    @Mappings({})
    PanelContentDto panelContent2Dto(PanelContent panelContent);

    @Mappings({})
    PanelContentDto panelContentItem2Dto(PanelContentItem panelContentItem);

    @Mappings({})
    PanelDto panen2Dto(Panel panel);

    List<PanelContentDto> panelContents2Dto(List<PanelContent> panelContents);

    List<PanelContentItemDto> panelContentItem2Dto(List<PanelContentItem> panelContentItems);

}
