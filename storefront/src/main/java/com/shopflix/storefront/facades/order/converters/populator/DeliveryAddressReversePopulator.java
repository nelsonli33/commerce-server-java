package com.shopflix.storefront.facades.order.converters.populator;

import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.delivery.DeliveryAddressModel;
import com.shopflix.core.model.user.CustomerAddressModel;
import com.shopflix.storefront.facades.user.converters.populator.AbstractAddressReversePopulator;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;

public class DeliveryAddressReversePopulator extends AbstractAddressReversePopulator<CustomerAddressData, DeliveryAddressModel>
{
    @Override
    public void populate(CustomerAddressData customerAddressData, DeliveryAddressModel deliveryAddressModel) throws ConversionException
    {
        addCommon(customerAddressData, deliveryAddressModel);
    }
}
