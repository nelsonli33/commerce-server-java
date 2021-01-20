package com.shopflix.core.response;

public class ApiError {
    private int code;
    private String message;
    private String detail;

    public ApiError(ErrorCode errorCode, String detail) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.detail = detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
