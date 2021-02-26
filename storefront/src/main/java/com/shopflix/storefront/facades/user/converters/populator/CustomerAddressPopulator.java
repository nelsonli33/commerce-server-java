package com.shopflix.storefront.facades.user.converters.populator;

import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.user.CustomerAddressModel;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;

public class CustomerAddressPopulator extends AbstractAddressPopulator<CustomerAddressModel, CustomerAddressData>
{
    @Override
    public void populate(CustomerAddressModel addressModel, CustomerAddressData addressData) throws ConversionException
    {
        addCommon(addressModel, addressData);

        if (addressModel.getCustomer() != null) {
            addressData.setCustomerId(addressModel.getCustomer().getId());
        }
    }
}
