package com.shopflix.storefront.services.customer;

import com.shopflix.core.model.user.CustomerModel;

public interface CustomerService
{
    CustomerModel getCustomerForUid(String uid);

    CustomerModel getCurrentCustomer();

    void setCurrentUser(CustomerModel customer);
}
