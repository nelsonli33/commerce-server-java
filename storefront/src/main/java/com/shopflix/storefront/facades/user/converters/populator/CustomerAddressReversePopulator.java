package com.shopflix.storefront.facades.user.converters.populator;

import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.user.CustomerAddressModel;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;

public class CustomerAddressReversePopulator extends AbstractAddressReversePopulator<CustomerAddressData, CustomerAddressModel>
{
    @Override
    public void populate(CustomerAddressData customerAddressData, CustomerAddressModel customerAddressModel) throws ConversionException
    {
        addCommon(customerAddressData, customerAddressModel);
    }
}
