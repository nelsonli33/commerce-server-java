package com.shopflix.core.repository.order;

import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.user.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartModel, Long>
{
    CartModel findCartModelByCustomer(CustomerModel customer);
}
