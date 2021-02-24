package com.shopflix.storefront.services.order.strategies.calculation;

import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;

public interface FindDeliveryCostStrategy
{
    Double getDeliveryCost(final AbstractOrderModel order);

    Double getDeliveryCost(DeliveryModeModel deliveryMode, AbstractOrderModel abstractOrder);
}
