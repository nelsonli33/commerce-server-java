package com.shopflix.core.controllers;


import com.shopflix.core.exception.ValidationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Base controller for all controllers. Provides common functionality for all controllers.
 */
public abstract class AbstractController {

    @Resource(name = "messageSource")
    private MessageSource messageSource;


    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void doBindingResult(BindingResult bindingResult) {
        Locale locale = LocaleContextHolder.getLocale();

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(err -> getMessageSource().getMessage(err, locale))
                    .collect(Collectors.toList());

            throw new ValidationException(errorMessages.toString());
        }
    }
}
