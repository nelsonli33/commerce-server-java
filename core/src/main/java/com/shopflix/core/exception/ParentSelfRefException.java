package com.shopflix.core.exception;

public class ParentSelfRefException extends RuntimeException {

    public ParentSelfRefException(String message) {
        super(message);
    }

    public ParentSelfRefException(String message, Throwable cause) {
        super(message, cause);
    }

}
