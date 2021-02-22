package com.shopflix.core.service.impl;

import com.shopflix.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "sessionService")
public class DefaultSessionService implements SessionService
{
    private HttpSession httpSession;


    @Override
    public void setAttribute(String name, Object value)
    {
        getCurrentSession().setAttribute(name, value);
    }

    @Override
    public <T> T getAttribute(String name)
    {
        return (T) getCurrentSession().getAttribute(name);
    }

    @Override
    public <T> T getOrLoadAttribute(String name, SessionAttributeLoader<T> loader)
    {
        validateParameterNotNullStandardMessage("loader", loader);
        T result = getAttribute(name);
        if (result == null) {
            synchronized (httpSession) {
                result = getAttribute(name);
                if (result == null) {
                    result = loader.load();
                    setAttribute(name, result);
                }
            }
        }

        return result;
    }


    public HttpSession getCurrentSession()
    {
        return httpSession;
    }

    @Autowired
    public void setHttpSession(HttpSession httpSession)
    {
        this.httpSession = httpSession;
    }


}
