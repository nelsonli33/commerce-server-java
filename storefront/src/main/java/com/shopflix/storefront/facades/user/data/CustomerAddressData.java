package com.shopflix.storefront.facades.user.data;

import com.shopflix.core.data.user.AddressData;

public class CustomerAddressData extends AddressData
{
    private static final long serialVersionUID = 8541929752263864177L;

    private Long customerId;

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }
}
