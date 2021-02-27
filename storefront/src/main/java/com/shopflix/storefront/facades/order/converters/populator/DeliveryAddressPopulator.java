package com.shopflix.storefront.facades.order.converters.populator;

import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.delivery.DeliveryAddressModel;
import com.shopflix.storefront.facades.order.data.DeliveryAddressData;
import com.shopflix.storefront.facades.user.converters.populator.AbstractAddressPopulator;

public class DeliveryAddressPopulator extends AbstractAddressPopulator<DeliveryAddressModel, DeliveryAddressData>
{
    @Override
    public void populate(DeliveryAddressModel source, DeliveryAddressData target) throws ConversionException
    {
        addCommon(source, target);
    }
}
