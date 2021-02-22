package com.shopflix.storefront.services.customer.impl;

import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.repository.user.CustomerRepository;
import com.shopflix.core.service.SessionService;
import com.shopflix.storefront.services.customer.CustomerService;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class DefaultCustomerService implements CustomerService
{
    private SessionService sessionService;
    private CustomerRepository customerRepository;

    @Override
    public CustomerModel getCustomerForUid(String uid)
    {
        return getCustomerRepository().findByUid(uid);
    }

    @Override
    public CustomerModel getCurrentCustomer()
    {
        return getSessionService().getAttribute("customer");
    }

    @Override
    public void setCurrentUser(CustomerModel customer)
    {
        validateParameterNotNullStandardMessage("customer", customer);
        CustomerModel previous = getCurrentCustomer();
        if (!customer.equals(previous))
        {
            getSessionService().setAttribute("customer", customer);
        }
    }

    public CustomerRepository getCustomerRepository()
    {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public SessionService getSessionService()
    {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService)
    {
        this.sessionService = sessionService;
    }
}
