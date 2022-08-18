package com.alan.mall.common.core.result;

import java.io.Serializable;

/**
 * @Auther: mathyoung
 * @description:
 */
public abstract class AbstractRequest implements Serializable {
    private static final long serialVersionUID = -5206880452462633700L;

    public abstract void requestCheck();
}
