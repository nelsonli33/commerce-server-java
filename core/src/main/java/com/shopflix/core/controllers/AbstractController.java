package com.shopflix.core.controllers;


import org.springframework.context.MessageSource;

import javax.annotation.Resource;

/**
 * Base controller for all controllers. Provides common functionality for all controllers.
 */
public abstract class AbstractController {

    @Resource(name = "messageSource")
    private MessageSource messageSource;


    public MessageSource getMessageSource() {
        return messageSource;
    }
}
