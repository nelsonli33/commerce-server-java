package com.shopflix.core.response;

public enum  ResultCode {

    SUCCESS(200, "成功"),
    NOT_FOUND(404, "資源不存在"),
    FORBIDDEN(403, "沒有訪問權限"),
    INVALID_REQUEST(400, "Invalid Request"),
    PARAM_ERROR(400, "請求參數錯誤"),
    TOKEN_ERROR(400, "請求簽名錯誤"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    UNKNOW_ERROR(999, "未知異常");

    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
