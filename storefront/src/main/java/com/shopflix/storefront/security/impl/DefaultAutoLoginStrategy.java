package com.shopflix.storefront.security.impl;

import com.shopflix.storefront.security.AutoLoginStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(value = "autoLoginStrategy")
public class DefaultAutoLoginStrategy implements AutoLoginStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAutoLoginStrategy.class);

    @Override
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            token.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(token);
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            LOG.error("Failure during autoLogin", e);
        }
    }
}
