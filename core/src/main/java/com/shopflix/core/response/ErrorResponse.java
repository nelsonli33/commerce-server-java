package com.shopflix.core.response;

public class ErrorResponse {
    private int code;
    private String message;
    private String detail;

    public ErrorResponse(ErrorCode errorCode, String detail) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getErrorMessage();
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
