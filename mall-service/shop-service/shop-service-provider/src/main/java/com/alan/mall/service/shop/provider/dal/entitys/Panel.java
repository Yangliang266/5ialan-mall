package com.alan.mall.service.shop.provider.dal.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_panel")
public class Panel implements Serializable {
    /**
     * 类目ID
     */
    @Id
    private Integer id;

    /**
     * 板块名称
     */
    private String name;

    /**
     * 类型 0轮播图 1板块种类一 2板块种类二 3板块种类三 
     */
    private Integer type;

    /**
     * 排列序号
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /**
     * 所属位置 0首页 1商品推荐 2我要捐赠
     */
    private Integer position;

    /**
     * 板块限制商品数量
     */
    @Column(name = "limit_num")
    private Integer limitNum;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    private static final long serialVersionUID = 1L;

    /**
     * 获取类目ID
     *
     * @return id - 类目ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置类目ID
     *
     * @param id 类目ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取板块名称
     *
     * @return name - 板块名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置板块名称
     *
     * @param name 板块名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类型 0轮播图 1板块种类一 2板块种类二 3板块种类三 
     *
     * @return type - 类型 0轮播图 1板块种类一 2板块种类二 3板块种类三 
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型 0轮播图 1板块种类一 2板块种类二 3板块种类三 
     *
     * @param type 类型 0轮播图 1板块种类一 2板块种类二 3板块种类三 
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取排列序号
     *
     * @return sort_order - 排列序号
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排列序号
     *
     * @param sortOrder 排列序号
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取所属位置 0首页 1商品推荐 2我要捐赠
     *
     * @return position - 所属位置 0首页 1商品推荐 2我要捐赠
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * 设置所属位置 0首页 1商品推荐 2我要捐赠
     *
     * @param position 所属位置 0首页 1商品推荐 2我要捐赠
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 获取板块限制商品数量
     *
     * @return limit_num - 板块限制商品数量
     */
    public Integer getLimitNum() {
        return limitNum;
    }

    /**
     * 设置板块限制商品数量
     *
     * @param limitNum 板块限制商品数量
     */
    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取更新时间
     *
     * @return updated - 更新时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置更新时间
     *
     * @param updated 更新时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}