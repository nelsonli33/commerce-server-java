package com.shopflix.merchant.facades.delivery.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.model.order.delivery.DeliveryModeValueModel;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeData;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeValueData;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;

public class MerchantDeliveryModePopulator implements Populator<DeliveryModeModel, DeliveryModeData>
{

    private Converter<DeliveryModeValueModel, DeliveryModeValueData> merchantDeliveryModeValueConverter;

    @Override
    public void populate(DeliveryModeModel source, DeliveryModeData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setActive(source.getActive());
        target.setModeType(source.getModeType() != null ? source.getModeType().getCode() : null);
        target.setModeSubType(source.getModeSubType() != null ? source.getModeSubType().getCode() : null);
        target.setTemperatureType(source.getTemperatureType() != null ? source.getTemperatureType().getCode() : null);

        addDeliveryModeValues(source, target);
    }

    protected void addDeliveryModeValues(DeliveryModeModel source, DeliveryModeData target) {
        final List<DeliveryModeValueData> deliveryModeValueData =
                getMerchantDeliveryModeValueConverter().convertAll(source.getTierConditions());
        deliveryModeValueData.sort(Comparator.comparingDouble(DeliveryModeValueData::getMinOrderTotal));

        target.setTierConditions(deliveryModeValueData);
    }


    public Converter<DeliveryModeValueModel, DeliveryModeValueData> getMerchantDeliveryModeValueConverter()
    {
        return merchantDeliveryModeValueConverter;
    }

    public void setMerchantDeliveryModeValueConverter(Converter<DeliveryModeValueModel, DeliveryModeValueData> merchantDeliveryModeValueConverter)
    {
        this.merchantDeliveryModeValueConverter = merchantDeliveryModeValueConverter;
    }
}
