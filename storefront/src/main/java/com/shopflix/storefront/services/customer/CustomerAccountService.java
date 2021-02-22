package com.shopflix.storefront.services.customer;
import com.shopflix.core.model.user.CustomerModel;

public interface CustomerAccountService {

    CustomerModel register(CustomerModel customerModel, String password);

}
