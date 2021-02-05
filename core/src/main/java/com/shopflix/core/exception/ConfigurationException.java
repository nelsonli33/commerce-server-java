package com.shopflix.core.exception;

public class ConfigurationException extends SystemException {
    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}