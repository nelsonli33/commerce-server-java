package com.shopflix.storefront.facades.user.impl;

import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.storefront.facades.user.CustomerFacade;
import com.shopflix.storefront.facades.user.data.RegisterData;
import com.shopflix.storefront.services.customer.CustomerAccountService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Component(value = "customerFacade")
public class DefaultCustomerFacade implements CustomerFacade {


    private CustomerAccountService customerAccountService;

    @Override
    public void register(RegisterData registerData) {
        validateParameterNotNullStandardMessage("registerData", registerData);
        Assert.hasText(registerData.getUsername(), "The field [Username] cannot be empty");
        Assert.hasText(registerData.getUid(), "The field [Uid] cannot be empty");

        CustomerModel newCustomer = new CustomerModel();
        newCustomer.setUsername(registerData.getUsername());
        newCustomer.setUid(registerData.getUid().toLowerCase());
        newCustomer.setEmail(registerData.getEmail());

        customerAccountService.register(newCustomer, registerData.getPassword());
    }




    @Resource(name = "customerAccountService")
    public void setCustomerAccountService(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    public CustomerAccountService getCustomerAccountService() {
        return customerAccountService;
    }


}
