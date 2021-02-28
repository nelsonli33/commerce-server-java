package com.shopflix.core.service.impl;

import com.shopflix.core.service.I18NService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "i18NService")
public class DefaultI18NService implements I18NService
{
    private MessageSource messageSource;

    @Override
    public String getLocalizedMessage(String code)
    {
        return getMessageSource().getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public MessageSource getMessageSource()
    {
        return messageSource;
    }

    @Resource(name = "messageSource")
    public void setMessageSource(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }
}
