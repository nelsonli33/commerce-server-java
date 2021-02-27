package com.shopflix.storefront.security;

import com.shopflix.storefront.services.customer.CustomerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StorefrontAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private CustomerService customerService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        final UserDetails principal = (UserDetails) authentication.getPrincipal();
        getCustomerService().setCurrentUser(getCustomerService().getCustomerForUid(principal.getUsername()));
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }
}
