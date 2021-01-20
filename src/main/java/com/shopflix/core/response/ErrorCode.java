package com.shopflix.core.response;

/**
 * https://developer.intuit.com/app/developer/qbo/docs/develop/troubleshooting/error-codes
 *
 */

/**
 * 0 Success response
 * 600-999 Authorization and authentication error codes
 * 1000-2999 General error codes
 * 3000-9999 Validation error codes
 * 10000+ System faults
 */

public enum ErrorCode {
    SUCCESS(0, "success"),

    ERROR_CODE_1000(1000, "Item Not Found"),
    ERROR_CODE_1010(1010, "Data conversion error"),
    ERROR_CODE_1020(1020, "Parent cannot be child");

    private int code;
    private String message;

    private ErrorCode(int code, String message) {
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
