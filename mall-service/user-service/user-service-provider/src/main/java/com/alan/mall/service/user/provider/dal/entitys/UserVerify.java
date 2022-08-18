package com.alan.mall.service.user.provider.dal.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user_verify")
public class UserVerify implements Serializable {
    @Id
    private Long id;

    private String username;

    @Column(name = "register_date")
    private Date registerDate;

    private String uuid;

    /**
     * 是否验证Y已验证，N为验证
     */
    @Column(name = "is_verify")
    private String isVerify;

    /**
     * 是否过期Y已过期，N为过期
     */
    @Column(name = "is_expire")
    private String isExpire;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return register_date
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * @param registerDate
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取是否验证Y已验证，N为验证
     *
     * @return is_verify - 是否验证Y已验证，N为验证
     */
    public String getIsVerify() {
        return isVerify;
    }

    /**
     * 设置是否验证Y已验证，N为验证
     *
     * @param isVerify 是否验证Y已验证，N为验证
     */
    public void setIsVerify(String isVerify) {
        this.isVerify = isVerify;
    }

    /**
     * 获取是否过期Y已过期，N为过期
     *
     * @return is_expire - 是否过期Y已过期，N为过期
     */
    public String getIsExpire() {
        return isExpire;
    }

    /**
     * 设置是否过期Y已过期，N为过期
     *
     * @param isExpire 是否过期Y已过期，N为过期
     */
    public void setIsExpire(String isExpire) {
        this.isExpire = isExpire;
    }
}