package com.shopflix.core.model.order;

import com.shopflix.core.model.user.CustomerModel;

import javax.persistence.*;

@Entity
@Table(name = "carts")
public class CartModel extends AbstractOrderModel {

    @OneToOne(fetch = FetchType.LAZY)
    private CustomerModel customer;

    public CustomerModel getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerModel customer)
    {
        this.customer = customer;
    }
}
