package com.shopflix.core.service;

public interface SessionService
{
    void setAttribute(String name, Object value);

    <T> T getAttribute(String name);

    <T> T getOrLoadAttribute(String name, SessionAttributeLoader<T> loader);

    interface SessionAttributeLoader<T> {
        T load();
    }
}
