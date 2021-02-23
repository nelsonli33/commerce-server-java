package com.shopflix.merchant.service.delivery;

import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.model.order.delivery.DeliveryModeValueModel;

import java.util.List;

public interface MerchantDeliveryModeService
{
    List<DeliveryModeModel> getAllDeliveryModes();

    DeliveryModeModel getDeliveryModeForId(Long id);

    DeliveryModeModel save(DeliveryModeModel model);

    void removeDeliveryMode(DeliveryModeModel model);

    DeliveryModeValueModel getDeliveryModeValueForId(final DeliveryModeModel mode, final Long modeValueId);
}
