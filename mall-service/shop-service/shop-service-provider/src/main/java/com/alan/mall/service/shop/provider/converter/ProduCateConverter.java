package com.alan.mall.service.shop.provider.converter;

import com.alan.mall.service.shop.api.dto.ProductCateDto;
import com.alan.mall.service.shop.provider.dal.entitys.ItemCat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProduCateConverter {
    @Mappings({
        @Mapping(source = "icon", target = "iconUrl")
    })
    ProductCateDto itemCat2Dto(ItemCat itemCat);

    List<ProductCateDto> itemCats2Dto(List<ItemCat> itemCats);

}
