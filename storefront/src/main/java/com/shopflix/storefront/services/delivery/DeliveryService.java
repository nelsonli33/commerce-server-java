package com.shopflix.storefront.services.delivery;

import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.core.model.order.delivery.DeliveryAddressModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;

import java.util.List;

public interface DeliveryService
{

    List<DeliveryModeModel> getSupportedDeliveryModesForOrder(AbstractOrderModel abstractOrder);

    DeliveryModeModel getDeliveryModeForCode(String code);

    Double getDeliveryCostForDeliveryModeAndOrder(final DeliveryModeModel deliveryMode, final AbstractOrderModel abstractOrder);

    Integer getAddressTypeForDeliveryMode(final DeliveryModeModel deliveryMode);
}
