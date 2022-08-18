package com.alan.mall.service.user.api.dto;

import com.alan.mall.common.core.result.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryMemberResponse extends AbstractResponse {
    private static final long serialVersionUID = -4254812312179537876L;
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;
    private String sex;
    private String address;
    private Integer state;
    private String file;
    private String description;
    private Integer points;
    private BigDecimal balance;
}
