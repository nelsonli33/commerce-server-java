package com.shopflix.merchant.facades.delivery;

import com.shopflix.merchant.facades.delivery.data.DeliveryModeData;

import java.util.List;

public interface MerchantDeliveryModeFacade
{
    List<DeliveryModeData> getAllDeliveryModes();

    DeliveryModeData getDeliveryModeForId(Long id);

    DeliveryModeData postDeliveryMode(DeliveryModeData deliveryModeData);

    DeliveryModeData editDeliveryMode(Long id, DeliveryModeData deliveryModeData);

    void removeDeliveryMode(Long id);

}
