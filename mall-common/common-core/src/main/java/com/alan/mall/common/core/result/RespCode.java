package com.alan.mall.common.core.result;


/**
 * @Auther: mathyoung
 * @description:
 */
public enum RespCode {
    SUCCESS("20000","操作成功",true),
    ERROR("50000", "操作失败",false),
    SYSTEM_ERROR("50001", "系统错误",false);

    private String code;
    private String message;
    private boolean status;

    RespCode(String code, String message, boolean status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
    RespCode() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
