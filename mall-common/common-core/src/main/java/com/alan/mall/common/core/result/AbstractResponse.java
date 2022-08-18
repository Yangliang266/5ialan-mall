package com.alan.mall.common.core.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: mathyoung
 * @description:
 */
@Data
public abstract class AbstractResponse implements Serializable {
    private static final long serialVersionUID = -50275229549415020L;
    private String code;
    private String msg;

}
