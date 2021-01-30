package com.shopflix.core.exception;

public class ParentSelfRefException extends RuntimeException {

    public ParentSelfRefException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ParentSelfRefException(String msg) {
        super(msg);
    }
}
