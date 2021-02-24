package com.shopflix.storefront.facades.order.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.storefront.data.order.DeliveryModeData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class DeliveryModePopulator implements Populator<DeliveryModeModel, DeliveryModeData>
{
    @Override
    public void populate(DeliveryModeModel source, DeliveryModeData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription("");
    }
}
