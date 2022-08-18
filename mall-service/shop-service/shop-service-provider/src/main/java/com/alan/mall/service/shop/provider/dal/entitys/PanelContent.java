package com.alan.mall.service.shop.provider.dal.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_panel_content")
public class PanelContent implements Serializable {
    @Id
    private Integer id;

    /**
     * 所属板块id
     */
    @Column(name = "panel_id")
    private Integer panelId;

    /**
     * 类型 0关联商品 1其他链接
     */
    private Integer type;

    /**
     * 关联商品id
     */
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "sort_order")
    private Integer sortOrder;

    /**
     * 其他链接
     */
    @Column(name = "full_url")
    private String fullUrl;

    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 3d轮播图备用
     */
    @Column(name = "pic_url2")
    private String picUrl2;

    /**
     * 3d轮播图备用
     */
    @Column(name = "pic_url3")
    private String picUrl3;

    private Date created;

    private Date updated;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所属板块id
     *
     * @return panel_id - 所属板块id
     */
    public Integer getPanelId() {
        return panelId;
    }

    /**
     * 设置所属板块id
     *
     * @param panelId 所属板块id
     */
    public void setPanelId(Integer panelId) {
        this.panelId = panelId;
    }

    /**
     * 获取类型 0关联商品 1其他链接
     *
     * @return type - 类型 0关联商品 1其他链接
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型 0关联商品 1其他链接
     *
     * @param type 类型 0关联商品 1其他链接
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取关联商品id
     *
     * @return product_id - 关联商品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置关联商品id
     *
     * @param productId 关联商品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return sort_order
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取其他链接
     *
     * @return full_url - 其他链接
     */
    public String getFullUrl() {
        return fullUrl;
    }

    /**
     * 设置其他链接
     *
     * @param fullUrl 其他链接
     */
    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    /**
     * @return pic_url
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * @param picUrl
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取3d轮播图备用
     *
     * @return pic_url2 - 3d轮播图备用
     */
    public String getPicUrl2() {
        return picUrl2;
    }

    /**
     * 设置3d轮播图备用
     *
     * @param picUrl2 3d轮播图备用
     */
    public void setPicUrl2(String picUrl2) {
        this.picUrl2 = picUrl2;
    }

    /**
     * 获取3d轮播图备用
     *
     * @return pic_url3 - 3d轮播图备用
     */
    public String getPicUrl3() {
        return picUrl3;
    }

    /**
     * 设置3d轮播图备用
     *
     * @param picUrl3 3d轮播图备用
     */
    public void setPicUrl3(String picUrl3) {
        this.picUrl3 = picUrl3;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}