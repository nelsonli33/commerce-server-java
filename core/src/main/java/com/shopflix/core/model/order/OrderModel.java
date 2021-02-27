package com.shopflix.core.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.model.user.CustomerModel;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderModel extends AbstractOrderModel
{
    @JsonBackReference
    @ManyToOne
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
