package com.shopflix.core.response;

/**
 * https://developer.intuit.com/app/developer/qbo/docs/develop/troubleshooting/error-codes
 */

/**
 * 0 Success response
 * 600-999 Authorization and authentication error codes
 * 1000-2999 General error codes
 * 3000-9999 Validation error codes
 * 10000+ System faults
 */

public enum ErrorCode {

    E_1000(1000, "Item Not Found"),
    E_1010(1010, "Data conversion error"),
    E_1020(1020, "Parent cannot be child"),

    E_3000(3000, "Validation error"),
    E_3001(3001, "Duplicate uid error");



    private int errorCode;
    private String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
