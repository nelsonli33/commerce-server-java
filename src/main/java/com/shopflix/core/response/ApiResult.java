package com.shopflix.core.response;

import java.io.Serializable;

public class ApiResult implements Serializable {

    private int code;
    private String message;
    private Object data;

    private ApiResult() {}

    private ApiResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static ApiResult success() {
        return new ApiResult(0, "success", null);
    }

    public static ApiResult success(Object data) {
        return new ApiResult(0, "success", data);
    }

    public static ApiResult error(ResultCode resultCode) {
        return new ApiResult(resultCode.getCode(), resultCode.getMessage(), null);
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
