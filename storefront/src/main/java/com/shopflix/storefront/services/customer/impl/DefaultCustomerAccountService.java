package com.shopflix.storefront.services.customer.impl;

import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.repository.CustomerRepository;
import com.shopflix.storefront.exceptions.DuplicateUidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "customerAccountService")
public class DefaultCustomerAccountService implements CustomerAccountService {

    private PasswordEncoder passwordEncoder;
    private CustomerRepository customerRepository;

    @Override
    public CustomerModel register(CustomerModel customerModel, String password) {
        validateParameterNotNullStandardMessage("customerModel", customerModel);

        CustomerModel customer = customerRepository.findByUid(customerModel.getUid());
        if (customer != null) {
            throw new DuplicateUidException(customerModel.getUid() + " already exist.");
        }

        if (password != null) {
            String encodePwd = passwordEncoder.encode(password);
            customerModel.setPassword(encodePwd);
        }

        return customerRepository.save(customerModel);
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }


}
