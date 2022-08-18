package com.alan.mall.portal.shop.forms;

import lombok.Data;

/**
 * @Auther: mathyoung
 * @description:
 */
@Data
public class OrderPay {
    private String orderId;
    private Integer status;
}
