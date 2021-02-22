package com.shopflix.storefront.services.order.impl;

import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.service.ModelService;
import com.shopflix.storefront.services.customer.CustomerService;
import com.shopflix.storefront.services.order.CartFactory;

import java.util.UUID;

public class DefaultCartFactory implements CartFactory
{
    private CustomerService customerService;
    private ModelService modelService;

    @Override
    public CartModel createCart()
    {
        final CustomerModel customer = customerService.getCurrentCustomer();
        final CartModel cart = new CartModel();
        // TODO: change to keyGenerator generator order number
        cart.setCode(UUID.randomUUID().toString());
        cart.setCustomer(customer);
        modelService.save(cart);
        return cart;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }
}
