package com.shopflix.storefront.security;

import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.repository.user.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "coreUserDetailsService")
public class CoreUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(CoreUserDetailsService.class);

    private String rolePrefix = "ROLE_";

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerModel customer = customerRepository.findByUid(username);

        if (customer == null) {
            LOG.warn("User not found");
            throw new UsernameNotFoundException("User not found!");
        }

        UserDetails userDetails = User.builder()
                .username(customer.getUid())
                .password(customer.getPassword())
                .roles("USER").build();

        return userDetails;
    }

}
