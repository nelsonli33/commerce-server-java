package com.shopflix.core.repository;

import com.shopflix.core.model.user.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    CustomerModel findByUid(String uid);
}
