package com.shopflix.merchant.facades.delivery.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.delivery.DeliveryModeValueModel;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeValueData;
import org.springframework.util.Assert;

public class MerchantDeliveryModeValuePopulator implements Populator<DeliveryModeValueModel, DeliveryModeValueData>
{
    @Override
    public void populate(DeliveryModeValueModel source, DeliveryModeValueData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        target.setId(source.getId());
        target.setDeliveryCost(source.getDeliveryCost());
        target.setMinOrderTotal(source.getMinOrderTotal());
    }
}
