package com.shopflix.core.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.model.base.AddressModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CustomerAddress")
public class CustomerAddressModel extends AddressModel
{
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
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
