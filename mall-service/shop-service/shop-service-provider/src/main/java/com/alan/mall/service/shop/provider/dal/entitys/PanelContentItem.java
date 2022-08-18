package com.alan.mall.service.shop.provider.dal.entitys;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
//@Table(name = "tb_panel_content_item")
public class PanelContentItem implements Serializable {
    private static final long serialVersionUID = 1L;

//    @Id
//    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private Integer panelId;

    private Integer type;

    private Long productId;

    private Integer sortOrder;

    private String fullUrl;

    private String picUrl;

    private String picUrl2;

    private String picUrl3;

    private Date created;

    private Date updated;

    private String productName;

    private BigDecimal salePrice;

    private String subTitle;
}
