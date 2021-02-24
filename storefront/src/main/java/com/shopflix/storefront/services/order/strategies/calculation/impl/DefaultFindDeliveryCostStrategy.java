package com.shopflix.storefront.services.order.strategies.calculation.impl;

import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.model.order.delivery.DeliveryModeValueModel;
import com.shopflix.storefront.services.order.strategies.calculation.FindDeliveryCostStrategy;

import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultFindDeliveryCostStrategy implements FindDeliveryCostStrategy
{
    @Override
    public Double getDeliveryCost(AbstractOrderModel abstractOrder)
    {
        return getDeliveryCost(abstractOrder.getDeliveryMode(), abstractOrder);
    }

    @Override
    public Double getDeliveryCost(DeliveryModeModel deliveryMode, AbstractOrderModel abstractOrder) {
        validateParameterNotNull(deliveryMode, "deliveryMode model cannot be null");
        validateParameterNotNull(abstractOrder, "abstractOrder model cannot be null");

        final Double subtotal = abstractOrder.getSubtotal();

        final List<DeliveryModeValueModel> tierConditions = deliveryMode.getTierConditions();

        tierConditions.sort((o1, o2) -> Double.compare(o2.getMinOrderTotal(), o1.getMinOrderTotal()));


        for(final DeliveryModeValueModel deliveryModeValue : tierConditions) {
            if (subtotal >= deliveryModeValue.getMinOrderTotal()) {
                return deliveryModeValue.getDeliveryCost();
            }
        }

        return Double.valueOf(0.0);
    }


}
