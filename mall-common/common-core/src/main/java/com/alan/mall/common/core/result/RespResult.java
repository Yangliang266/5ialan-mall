package com.alan.mall.common.core.result;

import lombok.Data;

/**
 * @Auther: mathyoung
 * @description:
 */
@Data
public class RespResult<T> {
    private T result;
    private String code;
    private String message;
    private boolean success;

    public RespResult() {
    }

    public RespResult(RespCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.success = resultCode.isStatus();
    }

    public RespResult(T result, RespCode resultCode) {
        this.result = result;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.success = resultCode.isStatus();
    }

    public RespResult(T result, RespCode resultCode, String msg) {
        this.result = result;
        this.code = resultCode.getCode();
        this.message = msg;
        this.success = resultCode.isStatus();
    }


    public static <T> RespResult<T> ok() {
        return new RespResult<T>(null, RespCode.SUCCESS);
    }

    public static <T> RespResult<T> ok(T resultData) {
        return new RespResult<T>(resultData, RespCode.SUCCESS);
    }

    public static <T> RespResult<T> ok(T resultData, String msg) {
        return new RespResult<T>(resultData, RespCode.SUCCESS, msg);
    }

    public static <T> RespResult<T> error() {
        return new RespResult<T>(null, RespCode.ERROR);
    }

    public static <T> RespResult<T> error(String message) {
        RespResult<T> err = new RespResult<T>();
        err.setCode(RespCode.ERROR.getCode());
        err.setMessage(message);
        err.setSuccess(RespCode.ERROR.isStatus());
        return err;
    }
}
