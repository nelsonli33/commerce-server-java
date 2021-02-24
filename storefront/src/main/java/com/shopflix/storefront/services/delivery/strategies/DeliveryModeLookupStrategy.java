package com.shopflix.storefront.services.delivery.strategies;

import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;

import java.util.List;

public interface DeliveryModeLookupStrategy
{
    List<DeliveryModeModel> getSelectableDeliveryModesForOrder(final AbstractOrderModel order);
}
